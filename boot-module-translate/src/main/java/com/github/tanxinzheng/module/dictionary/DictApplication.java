package com.github.tanxinzheng.module.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.github.tanxinzheng.**"})
public class DictApplication {

    public static void main(String[] args) {
        SpringApplication.run(DictApplication.class, args);
    }

}
