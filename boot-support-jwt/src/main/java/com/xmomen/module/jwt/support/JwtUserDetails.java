package com.xmomen.module.jwt.support;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by tanxinzheng on 2018/9/24.
 */
public interface JwtUserDetails extends UserDetails {

    void setToken(String token);

    void setRefreshToken(String refreshToken);

    String getToken();

    String getRefreshToken();

    String getName();

    String getUsername();
}
