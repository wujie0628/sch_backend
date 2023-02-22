package com.github.wujie0628.auth.authentication.service;

import com.github.wujie0628.auth.authentication.entity.Resource;
import org.springframework.security.access.ConfigAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public interface IResourceService {
    void loadResource();
    void saveResource(Resource resource);

    void removeResource(Resource resource);

    ConfigAttribute findConfigAttributeByUrl(HttpServletRequest authRequest);

    Set<Resource> queryByUsername(String name);
}
