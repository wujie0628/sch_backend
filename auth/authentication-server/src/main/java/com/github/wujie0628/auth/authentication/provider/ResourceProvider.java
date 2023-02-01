package com.github.wujie0628.auth.authentication.provider;

import com.github.wujie0628.auth.authentication.entity.Resource;
import com.github.wujie0628.common.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.w3c.dom.CharacterData;

import java.util.Set;

@FeignClient(name = "organization", fallback = ResourceProviderFallBack.class)
public interface ResourceProvider {

    @GetMapping("/resource/all")
    Result<Set<Resource>> getAllResources();

    @GetMapping("/resource/user/{username}")
    Result<Set<Resource>> resources(@PathVariable("username") String name);
}
