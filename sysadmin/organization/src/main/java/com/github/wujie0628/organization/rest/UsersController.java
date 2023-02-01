package com.github.wujie0628.organization.rest;


import com.github.wujie0628.common.entity.vo.Result;
import com.github.wujie0628.organization.config.TestRabbitMqConfig;
import com.github.wujie0628.organization.entity.po.User;
import com.github.wujie0628.organization.service.IUsersService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wujie
 * @since 2022-11-05
 */
@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    IUsersService usersService;
    @Autowired
    AmqpTemplate amqpTemplate;

    @GetMapping
    public Result<User> getByUniqueId(@RequestParam String uniqueId) {
        return Result.success(usersService.getByUniqueId(uniqueId));
    }

    @GetMapping("test")
    public List<String> test() {
        amqpTemplate.convertAndSend(TestRabbitMqConfig.EXCHEANGE_NAME
                ,"wj.key1"
                ,"hello");
        return Arrays.asList("1", "2", "3");
    }

}
