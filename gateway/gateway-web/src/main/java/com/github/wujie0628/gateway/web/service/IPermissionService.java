package com.github.wujie0628.gateway.web.service;

public interface IPermissionService {
    boolean hasPermission(String authorization, String url, String method);
}
