package com.github.wujie0628.organization.rest;


import com.github.wujie0628.common.entity.vo.Result;
import com.github.wujie0628.organization.entity.po.Resource;
import com.github.wujie0628.organization.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * <p>
 * 资源表 前端控制器
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    @GetMapping("/all")
    public Result queryAll() {
        return Result.success(resourceService.getAll());
    }

    @GetMapping("/user/{username}")
    public Result queryByUsername(@PathVariable String username) {
        return Result.success(resourceService.getByUsername(username));
    }
}
