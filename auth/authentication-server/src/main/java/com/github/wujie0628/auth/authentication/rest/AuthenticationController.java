package com.github.wujie0628.auth.authentication.rest;

import com.github.wujie0628.auth.authentication.service.IAuthenticationService;
import com.github.wujie0628.common.entity.vo.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@RestController
@RequestMapping("/auth")
@Api("auth")
@Slf4j
public class AuthenticationController {
    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/permission")
    public Result auth(@RequestParam String url, @RequestParam String method, HttpServletRequest request) {

        boolean decide = authenticationService.decide(new HttpServletRequestAuthWrapper(request, url, method));
        return Result.success(decide);
    }
}
