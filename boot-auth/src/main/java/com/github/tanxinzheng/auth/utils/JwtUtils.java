package com.github.tanxinzheng.auth.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by tanxinzheng on 2018/9/23.
 */
@Slf4j
public class JwtUtils {


    /**
     * 生成token
     * @param username
     * @return
     */
    public static String createToken(String username){
        return createTokenByTime(username, jwtConfigProperties.getExpiration());
    }

    /**
     * 生成token
     * @param username
     * @return
     */
    public static String createRefreshToken(String username, Long refreshTokenExpiration, String secret, String issuer){
        return createTokenByTime(username, refreshTokenExpiration, secret, issuer);
    }

    private static String createTokenByTime(String username, Long expiration, String secret, String issuer){
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .setSubject(username)
                .setNotBefore(new Date())
                .signWith(SignatureAlgorithm.HS256, secret);
        if(StringUtils.isNotBlank(issuer)){
            jwtBuilder.setIssuedAt(new Date());
            jwtBuilder.setIssuer(issuer);
        }
        return jwtBuilder.compact();
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
     * 获取用户名
     * @param token
     * @return
     */
    public String getUsernameByToken(String token, String secret){
        if(!validateToken(token)){
            return null;
        }
        return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody().getSubject();
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     */
    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 判断token是否已经过期
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
    private Claims getClaimsFromToken(String token, String secret) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (UnsupportedJwtException ex) {
            log.error("token验证失败: {}", token);
            log.error(ex.getMessage(), ex);
            throw new JwtException("the access token is illegal, signature parsing fail.");
        } catch (SignatureException | MalformedJwtException se){
            log.error(se.getMessage(), se);
            throw new JwtException("the access token is illegal, signature parsing fail.");
        } catch (ExpiredJwtException e){
            log.error(e.getMessage(), e);
            throw new JwtException("the access token is expired.");
        }
        return claims;
    }

}
