package com.github.tanxinzheng.module.auth.domain.dto;

import com.github.tanxinzheng.framework.web.annotation.AccountField;
import lombok.Data;

@Data
public class LoginResponse {

    @AccountField
    private String userId;
    private String name;
    private String username;
    private String accessToken;
    private String refreshToken;
}
