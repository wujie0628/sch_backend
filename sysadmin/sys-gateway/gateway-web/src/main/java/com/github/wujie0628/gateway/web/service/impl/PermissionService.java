package com.github.wujie0628.gateway.web.service.impl;

import com.github.wujie0628.auth.client.service.IAuthService;
import com.github.wujie0628.gateway.web.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    IAuthService authService;

    @Override
    public boolean hasPermission(String authorization, String url, String method) {
        return authService.hasPermission(authorization, url, method);
    }
}
