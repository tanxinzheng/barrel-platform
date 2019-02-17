package com.github.tanxinzheng.module.jwt.support;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * Created by tanxinzheng on 2018/9/23.
 */
public class JwtUtils {

    /**
     * 生成token
     * @param username
     * @param issuer
     * @param secret
     * @param expiration
     * @return
     */
    public static String createToken(String username, String issuer, String secret, Long expiration){
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
//                .setAudience(jwtUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
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
    public static Claims parseToken(String token, String secret){
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
        return claimsJws.getBody();
    }


    /**
     * 获取token
     * @param request
     * @param headerName
     * @param cookieName
     * @param parameterName
     * @return
     */
    public static String getTokenValue(HttpServletRequest request, String headerName, String cookieName, String parameterName){
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
