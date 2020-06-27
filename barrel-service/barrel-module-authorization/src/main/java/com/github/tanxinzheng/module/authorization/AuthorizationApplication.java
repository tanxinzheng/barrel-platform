package com.github.tanxinzheng.module.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringCloudApplication
@EnableFeignClients
@ComponentScan(basePackages = {
        "com.github.tanxinzheng.**",
        "springfox.documentation.swagger2.**"
})
public class AuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }

}
