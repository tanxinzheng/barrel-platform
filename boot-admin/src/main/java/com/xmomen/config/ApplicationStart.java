package com.xmomen.config;

import com.github.tanxinzheng.module.jwt.EnableJWT;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by tanxinzheng on 17/6/24.
 */
@ServletComponentScan
@SpringBootApplication
@EnableJWT
@MapperScan(value = "com.**.mapper")
public class ApplicationStart {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }

}
