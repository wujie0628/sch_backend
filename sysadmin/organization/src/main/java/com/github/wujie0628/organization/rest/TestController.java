package com.github.wujie0628.organization.rest;

import com.github.wujie0628.organization.entity.po.User;
import com.github.wujie0628.organization.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    IUsersService usersService;

    @GetMapping("test")
    public List<User> test() {
        return usersService.list();

    }
}
