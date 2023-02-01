package com.github.wujie0628.auth.authentication.service;

import javax.servlet.http.HttpServletRequest;

public interface IAuthenticationService {
    boolean decide(HttpServletRequest request);
}
