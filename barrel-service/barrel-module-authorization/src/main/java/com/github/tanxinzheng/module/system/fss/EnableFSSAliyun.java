package com.github.tanxinzheng.module.system.fss;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by tanxinzheng on 2018/9/20.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(FssAutoConfiguration.class)
@Documented
public @interface EnableFSSAliyun {
}
