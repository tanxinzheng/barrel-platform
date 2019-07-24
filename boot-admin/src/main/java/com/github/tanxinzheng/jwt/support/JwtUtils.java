package com.github.tanxinzheng.jwt.support;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * Created by tanxinzheng on 2018/9/23.
 */
@Component
@Slf4j
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 生成token
     * @param username
     * @param issuer
     * @return
     */
    public String createToken(String username, String issuer){
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
//                .setAudience(jwtUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .setIssuedAt(new Date())
                .setSubject(username)
                .setIssuer(issuer)
                .setNotBefore(new Date())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * 解析出jwt信息
     * @param token
     * @param secret
     * @return
     */
    public Claims parseToken(String token, String secret){
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
        return claimsJws.getBody();
    }

    /**
     * 获取用户名
     * @param token
     * @return
     */
    public String getUsernameByToken(String token){
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameByToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}",token);
        }
        return claims;
    }

    /**
     * 获取token
     * @param request
     * @param headerName
     * @param cookieName
     * @param parameterName
     * @return
     */
    public String getTokenValue(HttpServletRequest request, String headerName, String cookieName, String parameterName){
        if(request == null){
            return null;
        }
        // 从Header中获取token
        String token = request.getHeader(headerName);
        if(StringUtils.isNotBlank(token)){
            token = token.replaceAll(StringUtils.lowerCase(TokenType.BEARER.getCode()) + " ", "");
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




}
