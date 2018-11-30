package com.github.tanxinzheng.module.jwt.support.exception;

import io.jsonwebtoken.JwtException;

/**
 * Created by tanxinzheng on 2018/11/29.
 */
public class JwtTokenInvalidException extends JwtException {

    public JwtTokenInvalidException(String message) {
        super(message);
    }

    public JwtTokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
