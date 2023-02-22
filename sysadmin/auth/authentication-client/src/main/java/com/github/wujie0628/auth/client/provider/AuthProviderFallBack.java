package com.github.wujie0628.auth.client.provider;

import com.github.wujie0628.common.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthProviderFallBack implements AuthProvider{
    @Override
    public Result auth(String authorization, String url, String method) {
        log.error("调用鉴权服务触发熔断降级了。");
        return Result.fail();
    }
}
