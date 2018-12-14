package com.github.tanxinzheng.module.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by tanxinzheng on 2018/9/20.
 */
@Data
@ConfigurationProperties(value = "jwt")
public class JwtConfigProperties {

    private static final String DEFAULT_JWT_TOKEN_HEADER_NAME = "Authorization";

    private static final String DEFAULT_JWT_TOKEN_PARAMETER_NAME = "token";

    private static final String DEFAULT_JWT_TOKEN_COOKIE_NAME = "jwt-token";

    private static final String DEFAULT_JWT_REFRESH_TOKEN_HEADER_NAME = "refresh-token";

    private static final String DEFAULT_JWT_REFRESH_TOKEN_PARAMETER_NAME = "refresh-token";

    private static final String DEFAULT_JWT_REFRESH_TOKEN_COOKIE_NAME = "jwt-refresh-token";

    private static final String DEFAULT_JWT_SESSION_NAME = "jwt-session";

    // 默认 2小时
    private static final long DEFAULT_JWT_EXPIRATION = 2 * 1000 * 60 * 60;

    // 默认 2周
    private static final long DEFAULT_JWT_REFRESH_EXPIRATION = 2 * 7 * 24 * 1000 * 60 * 60;

    /**
     * JWT 认证请求头
     */
    private String tokenHeaderName;

    /**
     * JWT 请求参数
     */
    private String tokenParameterName;

    /**
     * JWT 请求参数
     */
    private String tokenCookieName;

    /**
     * JWT 刷新token请求头
     */
    private String refreshTokenHeaderName;

    /**
     * JWT 刷新token请求参数
     */
    private String refreshTokenParameterName;

    /**
     * JWT 刷新tokenCookie参数
     */
    private String refreshTokenCookieName;

    /**
     * JWT tokenId名称，用于获取缓存的角色，权限及其他敏感信息
     */
    private String tokenIdName;

    /**
     * 签发该证书的负责人
     */
    private String issuer;

    /**
     * JWT私钥
     */
    private String secret;

    /**
     * 有效时间
     */
    private Long expiration;

    /**
     * refresh token 有效时间
     */
    private Long refreshTokenExpiration;

    /**
     * 匿名可访问URL
     */
    private String[] permitUrls;

    public String getTokenHeaderName() {
        tokenHeaderName = tokenHeaderName == null ? DEFAULT_JWT_TOKEN_HEADER_NAME : tokenHeaderName;
        return tokenHeaderName;
    }

    public String getRefreshTokenHeaderName() {
        refreshTokenHeaderName = refreshTokenHeaderName == null ? DEFAULT_JWT_REFRESH_TOKEN_HEADER_NAME : refreshTokenHeaderName;
        return refreshTokenHeaderName;
    }

    public String getRefreshTokenParameterName() {
        refreshTokenParameterName = refreshTokenParameterName == null ? DEFAULT_JWT_REFRESH_TOKEN_PARAMETER_NAME : refreshTokenParameterName;
        return refreshTokenParameterName;
    }

    public String getRefreshTokenCookieName() {
        refreshTokenCookieName = refreshTokenCookieName == null ? DEFAULT_JWT_REFRESH_TOKEN_COOKIE_NAME : refreshTokenCookieName;
        return refreshTokenCookieName;
    }

    public void setTokenHeaderName(String tokenHeaderName) {
        this.tokenHeaderName = tokenHeaderName;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        expiration = expiration == null ? DEFAULT_JWT_EXPIRATION : expiration;
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getTokenParameterName() {
        tokenParameterName = tokenParameterName == null ? DEFAULT_JWT_TOKEN_PARAMETER_NAME : tokenParameterName;
        return tokenParameterName;
    }

    public void setTokenParameterName(String tokenParameterName) {
        this.tokenParameterName = tokenParameterName;
    }

    public String getTokenCookieName() {
        tokenCookieName = tokenCookieName == null ? DEFAULT_JWT_TOKEN_COOKIE_NAME : tokenCookieName;
        return tokenCookieName;
    }

    public void setTokenCookieName(String tokenCookieName) {
        this.tokenCookieName = tokenCookieName;
    }

    public String[] getPermitUrls() {
        return permitUrls;
    }

    public void setPermitUrls(String[] permitUrls) {
        this.permitUrls = permitUrls;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getTokenIdName() {
        tokenIdName = tokenIdName == null ? DEFAULT_JWT_SESSION_NAME : tokenIdName;
        return tokenIdName;
    }

    public void setTokenIdName(String tokenIdName) {
        this.tokenIdName = tokenIdName;
    }

    public Long getRefreshTokenExpiration() {
        refreshTokenExpiration = refreshTokenExpiration == null ? DEFAULT_JWT_REFRESH_EXPIRATION : refreshTokenExpiration;
        return refreshTokenExpiration;
    }

    public void setRefreshTokenExpiration(Long refreshTokenExpiration) {
        this.refreshTokenExpiration = refreshTokenExpiration;
    }
}
