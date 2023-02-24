package com.github.wujie0628.app.poetry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static com.github.wujie0628.app.poetry.constant.ProjectConstant.MAPPER_PACKAGE;

/**
 * 主程序
 *
 * @author Zoctan
 * @date 2018/05/27
 */
@EnableCaching
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = MAPPER_PACKAGE)
public class PoetryApplication extends SpringBootServletInitializer {

  public static void main(final String[] args) {
    SpringApplication.run(PoetryApplication.class, args);
  }

  /** 容器启动配置 */
  @Override
  protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
    return builder.sources(PoetryApplication.class);
  }
}
