package com.github.tanxinzheng.module.jwt;

import com.github.tanxinzheng.module.jwt.support.JwtUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanxinzheng on 2018/9/23.
 */
@EnableJWT
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@RestController
public class JwtStartApp {

    public static void main(String[] args) {
        SpringApplication.run(JwtStartApp.class, args);
    }

    @RequestMapping(value = "/email")
    public String testUserData(){
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return jwtUser.getEmail();
    }

    @RequestMapping(value = "/user")
    public String testUser(){
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return jwtUser.getName();
    }

    @RequestMapping(value = "/admin/user")
    public String testAdmin(){
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return jwtUser.getName();
    }

    @RequestMapping(value = "/anonymous")
    public String anonymous(){
        return "hello";
    }

}
