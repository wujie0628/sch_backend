package com.github.wujie0628.gateway.web.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.wujie0628.auth.client.service.IAuthService;
import com.github.wujie0628.common.entity.exception.SystemErrorType;
import com.github.wujie0628.gateway.web.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@ComponentScan(basePackages = "com.github.wujie0628.auth.client")
@Slf4j
public class GatewayFilter implements Ordered, GlobalFilter {

    public static final String SERVICE_KEY = "org.springframework.cloud.gateway.support.ServerWebExchangeUtils.gatewayPredicateMatchedPathRouteIdAttr";

    @Autowired
    IAuthService authService;
    @Autowired
    IPermissionService permissionService;

    /**
     * @param exchange 可以拿到对应的request和response
     * @param chain 过滤器链
     * @return 是否放行
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        String serviceName = exchange.getAttribute(SERVICE_KEY);
        String url = request.getPath().value().replaceAll("/"+serviceName,"");

        String mothod = request.getMethodValue();


        if(authService.ignoreAuthentication(url)){
            return chain.filter(exchange);
        }

        if(StrUtil.isEmpty(authorization)) {
            return out(exchange.getResponse());
        }

        if(permissionService.hasPermission(authorization, url, mothod)){
            return chain.filter(exchange.mutate().request(request.mutate().build()).build());
        }

        return out(exchange.getResponse());


    }

    private Mono<Void> out(ServerHttpResponse response) {
        JSONObject message = JSONUtil.createObj()
                .putOnce("code", SystemErrorType.INVALID_TOKEN.getCode())
                .putOnce("mesg",SystemErrorType.INVALID_TOKEN.getMesg())
                .putOnce("data", null);
        byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        //response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));

    }

    /**
     * 加载过滤器的顺序
     * @return 整数数字越小优先级越高
     */
    @Override
    public int getOrder() {
        return 0;
    }
}

