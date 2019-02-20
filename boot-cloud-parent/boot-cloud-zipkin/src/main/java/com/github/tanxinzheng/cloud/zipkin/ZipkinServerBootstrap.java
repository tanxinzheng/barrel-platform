package com.github.tanxinzheng.cloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.EnableZipkinServer;

/**
 * Created by tanxinzheng on 2019/1/28.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class ZipkinServerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerBootstrap.class, args);
    }
}
