package com.github.tanxinzheng.module.jwt.support.filter;

import com.github.tanxinzheng.module.jwt.support.JwtTokenService;
import com.github.tanxinzheng.module.jwt.support.JwtAuthenticationToken;
import com.github.tanxinzheng.module.jwt.support.exception.JwtTokenInvalidException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            // 验证token是否
            if (jwtTokenService.validToken(token)) {
                JwtAuthenticationToken authentication = jwtTokenService.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                throw new JwtTokenInvalidException("the access token is invalid.");
            }
        } catch (ExpiredJwtException expiredJwtException) {
            // token过期则验证refresh token
            String refreshToken = jwtTokenService.getRefreshToken(request);
//             refresh token过期则直接跳转登录页面
            if(refreshToken != null && !jwtTokenService.validToken(refreshToken)){
                // refresh token未过期则刷新token
                String newToken = jwtTokenService.updateToken(refreshToken, request, response);
                JwtAuthenticationToken authentication = jwtTokenService.getAuthentication(newToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e){
            logger.debug(e.getMessage(), e);
        }
        chain.doFilter(request, response);
    }

}
