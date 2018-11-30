package com.github.tanxinzheng.module.jwt.support;

import com.github.tanxinzheng.module.jwt.JwtConfigProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * Created by tanxinzheng on 17/8/18.
 */
@Slf4j
public class DefaultJwtTokenService implements JwtTokenService {

    public DefaultJwtTokenService(RedisTemplate redisTemplate, JwtConfigProperties jwtConfigProperties) {
        this.jwtConfigProperties = jwtConfigProperties;
        this.redisTemplate = redisTemplate;
    }

    private JwtConfigProperties jwtConfigProperties;

    private RedisTemplate redisTemplate;

    /**
     * 创建token
     *
     * @param username
     * @return
     */
    @Override
    public String createToken(String username) {
        return JwtUtils.createToken(username,
                jwtConfigProperties.getIssuer(),
                jwtConfigProperties.getSecret(),
                jwtConfigProperties.getExpiration());
    }

    /**
     * 获取token
     *
     * @param request
     * @return
     */
    @Override
    public String getToken(HttpServletRequest request) {
        return getTokenValue(
                request,
                jwtConfigProperties.getTokenHeaderName(),
                jwtConfigProperties.getTokenCookieName(),
                jwtConfigProperties.getTokenParameterName());
    }

    private String getTokenValue(HttpServletRequest request, String headerName, String cookieName, String parameterName){
        if(request == null){
            return null;
        }
        // 从Header中获取token
        String token = request.getHeader(headerName);
        if(StringUtils.isNotBlank(token)){
            token = token.replaceAll("Bearer ", "");
        }
        if(StringUtils.isBlank(token)){
            // 从请求参数中获取token
            token = request.getParameter(parameterName);
        }
        if(StringUtils.isBlank(token)
                && ArrayUtils.isNotEmpty(request.getCookies())){
            // 从Cookie中获取token
            String cookieTokenName = cookieName;
            for (Cookie cookie : request.getCookies()) {
                if(cookie.getName() != null
                        && cookieTokenName != null
                        && cookie.getName().equalsIgnoreCase(cookieTokenName)){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }

    /**
     * 获取refresh token
     *
     * @param request
     */
    @Override
    public String getRefreshToken(HttpServletRequest request) {
        return getTokenValue(
                request,
                jwtConfigProperties.getRefreshTokenHeaderName(),
                jwtConfigProperties.getRefreshTokenCookieName(),
                jwtConfigProperties.getRefreshTokenParameterName());
    }


    /**
     * 校验token
     *
     * @param token
     * @return
     */
    @Override
    public boolean validToken(String token) throws ExpiredJwtException {
        if(token == null){
            return false;
        }
        Jwts.parser().setSigningKey(jwtConfigProperties.getSecret()).parse(token);
        return true;
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    @Override
    public boolean validRefreshToken(String token) {
        if(token == null){
            return false;
        }
        try {
            Jwts.parser().setSigningKey(jwtConfigProperties.getSecret()).parse(token);
            return true;
        } catch (Exception e){
            log.debug(e.getMessage(), e);
        }
        return true;
    }

    /**
     * 更新token
     *
     * @param refreshToken
     * @param request
     * @param response
     */
    @Override
    public String updateToken(String refreshToken, HttpServletRequest request, HttpServletResponse response) {
        Claims claims = JwtUtils.parseToken(refreshToken, jwtConfigProperties.getSecret());
        String username = claims.getSubject();
        String newToken = createToken(username);
        response.setHeader("Bearer " + jwtConfigProperties.getTokenHeaderName(), newToken);
        return newToken;
    }


    /**
     * 删除token
     *
     * @param request
     */
    @Override
    public void removeToken(HttpServletRequest request, HttpServletResponse response) {
        String token = getToken(request);
        if(token == null){
            return;
        }
        try{
            Claims claims = JwtUtils.parseToken(token, jwtConfigProperties.getSecret());
            redisTemplate.delete(getJwtSessionName(claims.getSubject()));
        } catch (Exception e){
            log.debug(e.getMessage(), e);
        }
    }

    private String getJwtSessionName(String username){
        String cacheName = jwtConfigProperties.getTokenIdName() + ":" + username;
        return cacheName;
    }

    @Override
    public JwtAuthenticationToken getAuthentication(String token) {
        if(token == null){
            return null;
        }
        Claims claims = JwtUtils.parseToken(token, jwtConfigProperties.getSecret());
        String username = claims.getSubject();
        JwtUserDetails jwtUserDetails = (JwtUserDetails)
                redisTemplate.opsForValue().get(getJwtSessionName(username));
        if(jwtUserDetails == null){
            return null;
        }
        if(!token.equals(jwtUserDetails.getToken())){
            // TODO 是否允许同一账号多客户端同时登录，默认踢出之前登录的token
            throw new IllegalArgumentException("该账号已经在其他地方登录");
        }
        redisTemplate.opsForValue().set(
                getJwtSessionName(username),
                jwtUserDetails,
                2,
                TimeUnit.HOURS);
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwtUserDetails.getUsername(),
                jwtUserDetails.getToken(),
                jwtUserDetails.getRefreshToken(),
                jwtUserDetails,
                jwtUserDetails.getAuthorities());
        return jwtAuthenticationToken;
    }

    /**
     * 设置authentication信息
     *
     * @param authentication
     */
    @Override
    public void setAuthentication(Authentication authentication) {
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getDetails();
        redisTemplate.opsForValue().set(
                getJwtSessionName((String)authentication.getPrincipal()),
                jwtUserDetails,
                2,
                TimeUnit.HOURS);
    }
}
