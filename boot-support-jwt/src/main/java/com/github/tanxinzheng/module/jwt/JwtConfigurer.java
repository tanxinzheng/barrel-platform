package com.github.tanxinzheng.module.jwt;

import com.github.tanxinzheng.module.jwt.support.JwtLoadService;
import com.github.tanxinzheng.module.jwt.support.access.SecurityMetadataHandler;

/**
 * Created by tanxinzheng on 2018/9/24.
 */
public interface JwtConfigurer {

    JwtLoadService getJwtLoadService();


    SecurityMetadataHandler getSecurityMetadataHandler();
}
