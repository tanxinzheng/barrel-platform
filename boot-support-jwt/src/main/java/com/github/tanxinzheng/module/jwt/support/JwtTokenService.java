package com.github.tanxinzheng.module.jwt.support;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tanxinzheng on 17/6/15.
 */
public interface JwtTokenService {

    /**
     * 创建token
     * @param username
     * @return
     */
    public String createToken(String username);

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
     * @throws SignatureException
     * @throws ExpiredJwtException
     */
    public boolean validToken(String token) throws SignatureException, ExpiredJwtException;

    /**
     * 校验refresh token
     * @param refreshToken
     * @return
     * @throws SignatureException
     * @throws ExpiredJwtException
     */
    public boolean validRefreshToken(String refreshToken) throws SignatureException, ExpiredJwtException;

    /**
     * 更新token
     * @param refreshToken
     * @param request
     * @param response
     */
    public String updateToken(String refreshToken, HttpServletRequest request, HttpServletResponse response);

    /**
     * 删除token
     * @param request
     * @param response
     */
    void removeToken(HttpServletRequest request, HttpServletResponse response);
}
