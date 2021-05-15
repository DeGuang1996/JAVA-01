package com.geek.account.accounta;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableDubbo(scanBasePackages = "com.geek.account.accounta")
@ComponentScan(basePackages = {"com.geek.account.common", "com.geek.account.accounta", "com.geek.api"})
@MapperScan(basePackages = {"com.geek.dal.mapper"})
@SpringBootApplication
public class AccountaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountaApplication.class, args);
    }

}
