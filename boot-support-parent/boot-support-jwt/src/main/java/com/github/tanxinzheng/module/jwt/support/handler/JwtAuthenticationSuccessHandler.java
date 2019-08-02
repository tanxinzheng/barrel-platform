package com.github.tanxinzheng.module.jwt.support.handler;

import com.github.tanxinzheng.module.jwt.support.JwtUserDetails;
import com.github.tanxinzheng.module.jwt.support.RestResponse;
import com.github.tanxinzheng.module.jwt.support.TokenType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanxinzheng on 17/8/20.
 */
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String, Object> data = new HashMap<>();
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getDetails();
        data.put("name", jwtUserDetails.getName());
        data.put("username", jwtUserDetails.getUsername());
        data.put("tokenType", StringUtils.lowerCase(TokenType.BEARER.getCode()));
        data.put("accessToken", jwtUserDetails.getToken());
        data.put("refreshToken", jwtUserDetails.getRefreshToken());
        RestResponse.success(data).toJSON(request, response);
    }

}
