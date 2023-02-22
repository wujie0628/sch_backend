package com.github.wujie0628.auth.authorization.provider;

import com.github.wujie0628.auth.authorization.entity.Role;
import com.github.wujie0628.auth.authorization.entity.User;
import com.github.wujie0628.common.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "organization", fallback= OrganizationProviderFallBack.class)
public interface OrganizationProvider {

    @GetMapping(value ="/user")
    Result<User> getByUniqueId(@RequestParam("uniqueId") String uniqueId);

    @GetMapping(value ="/role/user/{userId}")
    Result<List<Role>> getRolesByUserId(@PathVariable("userId") String userId);

}
