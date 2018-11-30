package com.github.tanxinzheng.module.jwt.support.handler;

import com.github.tanxinzheng.module.jwt.support.JwtTokenService;
import com.github.tanxinzheng.module.jwt.support.RestResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tanxinzheng on 17/12/11.
 */
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

    public JwtLogoutSuccessHandler(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    private JwtTokenService jwtTokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        jwtTokenService.removeToken(request, response);
        RestResponse.ok("SUCCESS").toJSON(request, response);
    }
}
