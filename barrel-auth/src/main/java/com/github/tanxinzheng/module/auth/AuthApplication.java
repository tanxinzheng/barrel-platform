package com.github.tanxinzheng.module.auth;

import com.github.tanxinzheng.framework.web.config.AbstractSwaggerConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.github.tanxinzheng.**")
@ComponentScan(basePackages = {
        "com.github.tanxinzheng.**",
        "springfox.documentation.swagger2.**"
})
@MapperScan(basePackages = {
        "com.github.tanxinzheng.module.**.mapper.**"
})
@Configuration
@EnableSwagger2
public class AuthApplication extends AbstractSwaggerConfig {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Override
    public ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("基础功能管理平台-认证模块")
                .description("基础功能管理平台-认证模块")
                .contact(new Contact(
                        "谭新政",
                        "https://www.github.com/tanxinzheng",
                        "tanxinzheng@139.com"))
                .version("1.0")
                .build();
    }
}
