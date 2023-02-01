package com.github.wujie0628.organization.rest;


import com.github.wujie0628.common.entity.vo.Result;
import com.github.wujie0628.organization.entity.po.Role;
import com.github.wujie0628.organization.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
@RestController
@RequestMapping("/role")
public class RolesController {
    @Autowired
    IRolesService rolesService;


    @GetMapping("/user/{userId}")
    public Result<List<Role>> getRolesByUserId(@PathVariable String userId) {
        return Result.success(rolesService.getRolesByUserId(userId));
    }
}
