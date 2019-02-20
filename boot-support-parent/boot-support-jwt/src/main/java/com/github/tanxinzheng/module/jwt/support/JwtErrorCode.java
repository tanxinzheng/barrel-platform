package com.github.tanxinzheng.module.jwt.support;

/**
 * Created by tanxinzheng on 2018/11/30.
 */
public enum JwtErrorCode {
    TOKEN_NOT_FOUND("401001", "缺少access token"),
    TOKEN_INVALID("401002", "无效的access token"),
    TOKEN_EXPIRATION("401003", "过期的access token"),
    REFRESH_TOKEN_INVALID("401004", "无效的refresh token"),
    REFRESH_TOKEN_EXPIRATION("401005", "过期的refresh token"),
    ;
    //401001   缺少token             access token must be not null
    //401002   无效的token           invalid access token
    //401003   过期的token           expiration access token
    //401004   无效的refresh token   invalid refresh token
    //401005   过期的refresh token   expiration refresh token
    JwtErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
