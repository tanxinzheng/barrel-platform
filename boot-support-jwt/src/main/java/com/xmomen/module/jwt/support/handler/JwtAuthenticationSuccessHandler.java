package com.xmomen.module.jwt.support.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmomen.module.jwt.JwtConfigProperties;
import com.xmomen.module.jwt.support.JwtTokenService;
import com.xmomen.module.jwt.support.JwtUser;
import com.xmomen.module.jwt.support.JwtUserDetails;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanxinzheng on 17/8/20.
 */
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> data = new HashMap<>();
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getDetails();
        data.put("name", jwtUserDetails.getName());
        data.put("username", jwtUserDetails.getUsername());
        data.put("token", jwtUserDetails.getToken());
        data.put("refreshToken", jwtUserDetails.getRefreshToken());
        data.put("requestId", request.getRequestedSessionId());
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, data);
        out.flush();
    }

}
