package com.xmomen.module.jwt.support.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmomen.module.jwt.support.JwtAuthenticationToken;
import com.xmomen.module.jwt.support.JwtTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanxinzheng on 17/8/19.
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private JwtTokenService jwtTokenService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        super(authenticationManager);
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenService.getToken(request);
        try {
            if (jwtTokenService.validToken(token)) {
                JwtAuthenticationToken authentication = jwtTokenService.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("token is expired");
        } catch (Exception e){
            logger.debug(e.getMessage(), e);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            Map<String, Object> data = new HashMap<>();
            data.put("timestamp", new Date().getTime());
            data.put("message", e.getMessage());
            data.put("code", HttpStatus.UNAUTHORIZED.value());
            data.put("path",   request.getRequestURI());
            data.put("requestId", request.getRequestedSessionId());
            OutputStream out = response.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(out, data);
            out.flush();
        }
    }

}
