package com.github.wujie0628.auth.authentication.config;

import com.github.wujie0628.auth.authentication.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ResourceLoadDefine {

    @Autowired
    private IResourceService resourceService;

    @PostConstruct
    public void resourceConfigAttributes() {
        resourceService.loadResource();
    }
}
