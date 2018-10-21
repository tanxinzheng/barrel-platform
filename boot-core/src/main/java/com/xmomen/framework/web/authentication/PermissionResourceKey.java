package com.xmomen.framework.web.authentication;

import java.lang.annotation.*;

/**
 * Created by tanxinzheng on 2018/10/21.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PermissionResourceKey {

    String code();

    String description();
}
