package com.xmomen.module.jwt.support;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tanxinzheng on 17/6/15.
 */
public interface JwtTokenService {

    /**
     * 获取token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request);

    /**
     * 获取refresh token
     * @param request
     */
    public String getRefreshToken(HttpServletRequest request);

    /**
     * 获取Authentication
     * @param token
     * @return
     */
    public JwtAuthenticationToken getAuthentication(String token);

    /**
     * 设置authentication信息
     * @param authentication
     */
    public void setAuthentication(Authentication authentication);

    /**
     * 校验token
     * @param token
     * @return
     */
    public boolean validToken(String token);

    /**
     * 删除token
     * @param request
     * @param response
     */
    void removeToken(HttpServletRequest request, HttpServletResponse response);
}
