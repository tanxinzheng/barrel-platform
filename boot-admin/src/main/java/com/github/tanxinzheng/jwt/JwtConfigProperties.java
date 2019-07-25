package com.github.tanxinzheng.jwt;

import com.github.tanxinzheng.jwt.support.TokenType;
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

    private static final String DEFAULT_JWT_TOKEN_TYPE = TokenType.BEARER.getCode();

    // 默认 2小时
    private static final long DEFAULT_JWT_EXPIRATION = 2L * 1000 * 60 * 60;

    // 默认 2周
    private static final long DEFAULT_JWT_REFRESH_EXPIRATION = 2L * 7 * 24 * 1000 * 60 * 60;

    /**
     * JWT 认证请求头
     */
    private String tokenHeaderName;

    /**
     * Token Type
     */
    private String tokenType;

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

    public JwtConfigProperties() {
        this.tokenType = TokenType.BEARER.getCode();
        this.tokenHeaderName = DEFAULT_JWT_TOKEN_HEADER_NAME;
        this.tokenParameterName = DEFAULT_JWT_TOKEN_PARAMETER_NAME;
        this.tokenCookieName = DEFAULT_JWT_TOKEN_COOKIE_NAME;
        this.refreshTokenExpiration = DEFAULT_JWT_REFRESH_EXPIRATION;
        this.refreshTokenHeaderName = DEFAULT_JWT_REFRESH_TOKEN_HEADER_NAME;
        this.refreshTokenParameterName = DEFAULT_JWT_REFRESH_TOKEN_PARAMETER_NAME;
        this.refreshTokenCookieName = DEFAULT_JWT_REFRESH_TOKEN_COOKIE_NAME;
        this.tokenIdName = DEFAULT_JWT_SESSION_NAME;
        this.expiration = DEFAULT_JWT_EXPIRATION;
    }
}
