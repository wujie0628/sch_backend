package com.github.wujie0628.auth.client.provider;

import com.github.wujie0628.common.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "authentication-server", fallback = AuthProviderFallBack.class)
public interface AuthProvider {

    @PostMapping("/auth/permission")
    Result auth(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                @RequestParam("url") String url, @RequestParam("method") String method);
}
