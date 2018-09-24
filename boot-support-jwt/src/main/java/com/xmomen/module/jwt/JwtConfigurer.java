package com.xmomen.module.jwt;

import com.xmomen.module.jwt.support.JwtLoadService;

/**
 * Created by tanxinzheng on 2018/9/24.
 */
public interface JwtConfigurer {

    JwtLoadService getJwtLoadService();
}
