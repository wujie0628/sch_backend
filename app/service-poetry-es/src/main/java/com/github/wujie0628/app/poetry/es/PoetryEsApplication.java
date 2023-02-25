package com.github.wujie0628.app.poetry.es;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static com.github.wujie0628.app.poetry.es.constant.ProjectConstant.MAPPER_PACKAGE;

/**
 * 主程序
 *
 * @author Zoctan
 * @date 2018/05/27
 */
@EnableCaching
@SpringBootApplication(exclude= {DruidDataSourceAutoConfigure.class})
public class PoetryEsApplication extends SpringBootServletInitializer {

  public static void main(final String[] args) {
    SpringApplication.run(PoetryEsApplication.class, args);
  }


}
