package com.github.wujie0628.auth.authentication.service.impl;

import com.github.wujie0628.auth.authentication.entity.Resource;
import com.github.wujie0628.auth.authentication.service.IAuthenticationService;
import com.github.wujie0628.auth.authentication.service.IResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Service
@Slf4j
public class AuthenticationService implements IAuthenticationService {
    /**
     * 未在资源库中的URL默认标识
     */
    public static final String NONEXISTENT_URL = "NONEXISTENT_URL";

    @Autowired
    private IResourceService resourceService;

    @Override
    public boolean decide(HttpServletRequest request) {
        ConfigAttribute configAttributeByUrl = resourceService.findConfigAttributeByUrl(request);

        if(NONEXISTENT_URL.equals(configAttributeByUrl.getAttribute())) {
            log.debug("资源池内没有此url:{},{}", request.getRequestURI(),request.getMethod());
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Set<Resource> userResources = resourceService.queryByUsername(authentication.getName());

        return isMatch(configAttributeByUrl, userResources);
    }

    private boolean isMatch(ConfigAttribute configAttributeByUrl, Set<Resource> userResources) {
        return userResources.stream().anyMatch(resource -> resource.getCode().equals(configAttributeByUrl.getAttribute()));
    }
}
