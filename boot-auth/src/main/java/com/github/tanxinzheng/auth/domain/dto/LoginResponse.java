package com.github.tanxinzheng.auth.domain.dto;

import lombok.Data;

@Data
public class LoginResponse {

    private String name;
    private String username;
    private String accessToken;
    private String refreshToken;
}
