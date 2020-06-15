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
     * @param secret
     * @param expiration
     * @param issuer
     * @param claims
     * @return
     */
    private static String createToken(String username, String secret, Long expiration, String issuer, Claims claims){
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .setSubject(username)
                .setNotBefore(new Date());
        if(claims != null){
            jwtBuilder.setClaims(claims);
        }
        if(StringUtils.isNotBlank(issuer)){
            jwtBuilder.setIssuedAt(new Date());
            jwtBuilder.setIssuer(issuer);
        }
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secret);
        return jwtBuilder.compact();
    }

    /**
     * 生成token
     * @param username
     * @param secret
     * @param tokenExpiration
     * @param issuer
     * @return
     */
    public static String createToken(String username, String secret, Long tokenExpiration, String issuer){
        return createToken(username, secret, tokenExpiration, issuer, null);
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
     * 获取Claims
     * @param token
     * @param secret
     * @return
     */
    public Claims getClaimsByToken(String token, String secret){
        if(!validateToken(token, secret)){
            return null;
        }
        return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
    }

    /**
     * 获取Claims
     * @param token
     * @param secret
     * @return
     */
    public String getUsernameByToken(String token, String secret){
        Claims claims = getClaimsByToken(token, secret);
        return claims.getSubject();
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     */
    public boolean validateToken(String token, String secret) {
        return !isTokenExpired(token, secret);
    }

    /**
     * 判断token是否已经过期
     */
    private boolean isTokenExpired(String token, String secret) {
        Date expiredDate = getExpiredDateFromToken(token, secret);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token, String secret) {
        Claims claims = getClaimsFromToken(token, secret);
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
