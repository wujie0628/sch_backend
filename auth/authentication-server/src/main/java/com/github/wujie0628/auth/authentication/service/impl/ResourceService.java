package com.github.wujie0628.auth.authentication.service.impl;

import cn.hutool.json.JSONUtil;
import com.github.wujie0628.auth.authentication.entity.Resource;
import com.github.wujie0628.auth.authentication.provider.ResourceProvider;
import com.github.wujie0628.auth.authentication.service.IResourceService;
import com.github.wujie0628.common.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ResourceService implements IResourceService {

    @Autowired
    private ResourceProvider resourceProvider;

    @Autowired
    private HandlerMappingIntrospector handlerMappingIntrospector;

    private static final Map<RequestMatcher, ConfigAttribute> resourceConfigAttribute = new ConcurrentHashMap<>();

    @Override
    public void loadResource() {
        Result<Set<Resource>> resourcesResult = resourceProvider.getAllResources();
        log.info("项目启动获取到的资源集合为：{}", JSONUtil.toJsonStr(resourcesResult));

        if(resourcesResult.isFail()) {
            System.exit(1);
        }
        Set<Resource> resources = resourcesResult.getData();
        Map<RequestMatcher, SecurityConfig> temResourceConfigAttributes = resources.stream().collect(Collectors.toMap(
                resource -> this.getRequestMatcher(resource.getUrl(), resource.getMethod()),
                resource -> new SecurityConfig(resource.getCode())
        ));
        resourceConfigAttribute.putAll(temResourceConfigAttributes);
        log.info("初始化resourceConfigAttribute成功：{}",resourceConfigAttribute);
    }

    @Override
    public void saveResource(Resource resource) {
        resourceConfigAttribute.put(this.getRequestMatcher(resource.getUrl(), resource.getMethod()),
                new SecurityConfig(resource.getCode()));
        log.info("新增资源成功，目前资源条数为：{}", resourceConfigAttribute.size());
    }

    @Override
    public void removeResource(Resource resource) {
        resourceConfigAttribute.remove(this.getRequestMatcher(resource.getUrl(), resource.getMethod()));
    }

    @Override
    public ConfigAttribute findConfigAttributeByUrl(HttpServletRequest authRequest) {
        return resourceConfigAttribute.keySet().stream()
                .filter(requestMatcher -> requestMatcher.matches(authRequest))
                .map(requestMatcher -> resourceConfigAttribute.get(requestMatcher))
                .peek(urlconfigAttribute -> log.info("查找到对应资源：{}", urlconfigAttribute.getAttribute()))
                .findFirst()
                .orElse(new SecurityConfig("NONEXISTENT_URL"));
    }

    @Override
    public Set<Resource> queryByUsername(String name) {
        return resourceProvider.resources(name).getData();
    }


    private RequestMatcher getRequestMatcher(String url, String method) {
        return new AntPathRequestMatcher(url, method);
    }

}
