package com.xmomen.module.jwt;

import com.xmomen.module.jwt.support.JwtLoadService;
import com.xmomen.module.jwt.support.access.SecurityMetadataHandler;

/**
 * Created by tanxinzheng on 2018/9/24.
 */
public interface JwtConfigurer {

    JwtLoadService getJwtLoadService();


    SecurityMetadataHandler getSecurityMetadataHandler();
}
