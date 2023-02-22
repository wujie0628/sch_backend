package com.github.wujie0628.auth.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Oauth2AuthAuthenticationApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthAuthenticationApplication.class, args);
    }
}
