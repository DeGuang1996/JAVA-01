package com.geek.account.accountb;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableDubbo(scanBasePackages = "com.geek.account.accountb")
@ComponentScan(basePackages = {"com.geek.account.common", "com.geek.account.accountb", "com.geek.api"})
@MapperScan("com.geek.dal")
@SpringBootApplication
public class AccountbApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountbApplication.class, args);
    }

}
