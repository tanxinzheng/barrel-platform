package com.github.tanxinzheng.module.jwt.support;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
    public static String createToken(String username, String issuer, String secret, String expiration){
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
//                .setAudience(jwtUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + Long.valueOf(expiration) * 1000 * 60 * 60))
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




}
