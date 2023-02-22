package com.github.wujie0628.auth.client.service;

public interface IAuthService {
    boolean ignoreAuthentication(String url);

    boolean hasPermission(String authorization, String url, String method);
}
