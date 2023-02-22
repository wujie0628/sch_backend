package com.github.wujie0628.auth.client.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.github.wujie0628.auth.client.provider.AuthProvider;
import com.github.wujie0628.auth.client.service.IAuthService;
import com.github.wujie0628.common.entity.vo.Result;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@Slf4j
public class AuthService implements IAuthService {
    /**
     * Authorization认证开头是"bearer "
     */
    private static final String BEARER = "Bearer ";

    @Autowired
    private AuthProvider authProvider;

    @Value("${gate.ignore.authentication.startWith}")
    private String ignoreUrls;

    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey;

    @Override
    public boolean ignoreAuthentication(String url) {
        return Arrays.stream(ignoreUrls.split(",")).anyMatch(ignoreUrl -> url.startsWith(ignoreUrl.trim()));
    }

    @Override
    public boolean hasPermission(String authorization, String url, String method) {

        if(!invalidJwtAccessToken(authorization)) {
            return false;
        }

        return hasPermission(authenticate(authorization, url, method));
    }

    private boolean hasPermission(Result hasPermissionResult) {
        return hasPermissionResult.isSuccess() && (boolean) hasPermissionResult.getData();
    }

    private Result authenticate(String authorization, String url, String method){

        Future<Result> submit = ThreadUtil.newExecutor().submit(() -> authProvider.auth(authorization, url, method));
        try {
            Result r = submit.get();
            return r;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
//
//        return authProvider.auth(authorization, url, method);
    }

    private boolean invalidJwtAccessToken(String authorization) {

        boolean flag = false;

        try {
            getJwt(authorization);
            flag = true;
        }catch (SignatureException | ExpiredJwtException | MalformedJwtException ex) {
            log.error("token error:{}", ex.getMessage());
        }


        return flag;
    }

    private Jws<Claims> getJwt(String authorization) {

        if(authorization.startsWith(BEARER)) {
            authorization = StrUtil.subAfter(authorization, BEARER, true);
        }
        return Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(signingKey.getBytes()) //设置签名的秘钥
                .parseClaimsJws(authorization);
    }

}