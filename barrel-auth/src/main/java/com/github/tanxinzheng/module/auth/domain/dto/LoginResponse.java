package com.github.tanxinzheng.module.auth.domain.dto;

import lombok.Data;

@Data
public class LoginResponse {

    private String name;
    private String username;
    private String accessToken;
    private String refreshToken;
}
