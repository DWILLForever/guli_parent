package com.dwill.edu.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
//扫描mapper层
@Configuration
@MapperScan("com.dwill.edu.mapper")
public class EduConfig {
}
