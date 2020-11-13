package com.dwill.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//SpringBoot启动类
@SpringBootApplication
//如果不加，其它模块的配置不会扫描到
@ComponentScan(basePackages = {"com.dwill"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
