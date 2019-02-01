package com.github.tanxinzheng.module.jwt.support.filter;

import com.github.tanxinzheng.module.jwt.support.JwtErrorCode;
import com.github.tanxinzheng.module.jwt.support.JwtTokenService;
import com.github.tanxinzheng.module.jwt.support.JwtAuthenticationToken;
import com.github.tanxinzheng.module.jwt.support.RestResponse;
import com.github.tanxinzheng.module.jwt.support.exception.JwtTokenInvalidException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.method.P;
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
@Slf4j
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
        try {
            String token = jwtTokenService.getToken(request);
            if(StringUtils.isBlank(token)){
                chain.doFilter(request, response);
                return;
            }
            // 验证token是否
            if (jwtTokenService.validToken(token)) {
                JwtAuthenticationToken authentication = jwtTokenService.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (MalformedJwtException | SignatureException signatureException) {
            returnErrorCode(request, response, JwtErrorCode.TOKEN_INVALID);
            return;
        } catch (ExpiredJwtException expiredJwtException) {
            // token过期则验证refresh token
            String refreshToken = jwtTokenService.getRefreshToken(request);
            if(refreshToken == null) {
                // 不存在refresh token则提示token已过期
                returnErrorCode(request, response, JwtErrorCode.TOKEN_EXPIRATION);
                return;
            }
            if(jwtTokenService.validRefreshToken(refreshToken)){
                // refresh token有效且未过期则刷新token
                String newToken = jwtTokenService.updateToken(refreshToken, request, response);
                JwtAuthenticationToken authentication = jwtTokenService.getAuthentication(newToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                // refresh token无效或过期则提示错误代码
                returnErrorCode(request, response, JwtErrorCode.REFRESH_TOKEN_INVALID);
                return;
            }
        } catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        chain.doFilter(request, response);
    }

    private void returnErrorCode(HttpServletRequest request, HttpServletResponse response, JwtErrorCode errorCode) {
        try {
            RestResponse restResponse = new RestResponse();
            restResponse.setCode(errorCode.getCode());
            restResponse.setMessage(errorCode.getMessage());
            restResponse.setError(StringUtils.lowerCase(HttpStatus.UNAUTHORIZED.name()));
            restResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            restResponse.toJSON(request, response, HttpStatus.UNAUTHORIZED);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
