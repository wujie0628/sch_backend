package com.github.wujie0628.auth.authorization.provider;

import com.github.wujie0628.auth.authorization.entity.Role;
import com.github.wujie0628.auth.authorization.entity.User;
import com.github.wujie0628.common.entity.vo.Result;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class OrganizationProviderFallBack implements OrganizationProvider{
    @Override
    public Result<User> getByUniqueId(String uniqueId) {
        User user = new User();
        user.setName("这个人不存在，说明触发熔断降级了!");
        return Result.fail(user);
    }

    @Override
    public Result<List<Role>> getRolesByUserId(String userId) {
        return Result.fail(Collections.EMPTY_SET);
    }
}
