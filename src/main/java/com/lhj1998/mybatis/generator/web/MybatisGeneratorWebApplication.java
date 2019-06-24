package com.lhj1998.mybatis.generator.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MybatisGeneratorWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisGeneratorWebApplication.class, args);
    }

}
