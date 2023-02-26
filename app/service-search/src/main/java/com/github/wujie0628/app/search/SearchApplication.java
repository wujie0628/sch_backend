package com.github.wujie0628.app.search;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients(basePackages = {"com.github.wujie0628.app.poetry.feign", "com.github.wujie0628.app.poetry.es.feign"})
public class SearchApplication  extends SpringBootServletInitializer {
    public static void main(final String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

    /** 容器启动配置 */
    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return builder.sources(SearchApplication.class);
    }
}
