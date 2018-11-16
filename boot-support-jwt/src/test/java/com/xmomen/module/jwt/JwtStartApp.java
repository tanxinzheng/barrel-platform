package com.xmomen.module.jwt;

import com.xmomen.module.jwt.support.JwtUser;
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


    @RequestMapping(value = "/user")
    public JwtUser testUser(){
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return jwtUser;
    }

    @RequestMapping(value = "/admin/user")
    public JwtUser testAdmin(){
        JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return jwtUser;
    }

    @RequestMapping(value = "/anonymous")
    public String anonymous(){
        return "hello";
    }

}
