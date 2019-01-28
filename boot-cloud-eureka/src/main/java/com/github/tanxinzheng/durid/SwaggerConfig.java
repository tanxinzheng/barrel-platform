package com.github.tanxinzheng.durid;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by tanxinzheng on 2019/1/27.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo bootApiInfo() {
        return new ApiInfoBuilder()
                .title("Boot Platform API")//大标题
                .description("Boot平台Restful API接口文档")//详细描述
                .version("1.0")//版本
                .contact(new Contact("tanxinzheng", "http://www.github.com/tanxinzheng", "tanxinzheng@139.com"))//作者
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }

}
