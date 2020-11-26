package com.github.tanxinzheng.module.system;

import com.github.tanxinzheng.framework.web.config.AbstractSwaggerConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

@EnableDiscoveryClient
@SpringCloudApplication
@EnableFeignClients
@ComponentScan(basePackages = {
        "com.github.tanxinzheng.**",
        "springfox.documentation.swagger2.**"
})
@MapperScan(basePackages = {
        "com.github.tanxinzheng.module.**.mapper.**"
})
public class SystemApplication extends AbstractSwaggerConfig {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

    @Override
    public ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("基础平台-系统模块")
                .description("基础平台-系统模块")
                .contact(new Contact(
                        "谭新政",
                        "https://www.github.com/tanxinzheng",
                        "tanxinzheng@139.com"))
                .version("1.0")
                .build();
    }
}
