package com.xmomen.module.jwt.support.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmomen.module.jwt.support.JwtTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> data = new HashMap<>();
        data.put("timestamp", new Date().getTime());
        data.put("code", HttpStatus.OK.value());
        data.put("message", "logout success");
        data.put("requestId", request.getRequestedSessionId());
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, data);
        out.flush();
    }
}
