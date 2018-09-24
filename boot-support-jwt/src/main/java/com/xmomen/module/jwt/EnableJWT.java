package com.xmomen.module.jwt;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by tanxinzheng on 2018/9/20.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(JwtAutoConfiguration.class)
@Documented
public @interface EnableJWT {
}
