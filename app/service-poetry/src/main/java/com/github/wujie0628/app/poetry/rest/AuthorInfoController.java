package com.github.wujie0628.app.poetry.rest;


import com.github.wujie0628.app.poetry.entity.po.AuthorInfo;
import com.github.wujie0628.app.poetry.service.IAuthorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wujie
 * @since 2023-02-01
 */
@RestController
@RequestMapping("/author-info")
public class AuthorInfoController {

    @Autowired
    private IAuthorInfoService authorInfoService;

    @GetMapping("/test")
    public List<AuthorInfo> test() {
        return authorInfoService.list();
    }

}
