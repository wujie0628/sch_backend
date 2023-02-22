package com.github.wujie0628.auth.authentication.provider;

import com.github.wujie0628.auth.authentication.entity.Resource;
import com.github.wujie0628.common.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class ResourceProviderFallBack implements ResourceProvider{
    @Override
    public Result<Set<Resource>> getAllResources() {
      log.error("调用组织服务获取所有权限信息触发熔断限流了！");
      return Result.fail();
    }

    @Override
    public Result<Set<Resource>> resources(String name) {
        log.error("调用组织服务获取某个用户拥有的权限信息触发熔断限流了！");
        return Result.fail();
    }
}
