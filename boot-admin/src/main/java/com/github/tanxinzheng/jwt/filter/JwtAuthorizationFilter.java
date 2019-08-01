package com.github.tanxinzheng.jwt.filter;

import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import com.github.tanxinzheng.jwt.JwtConfigProperties;
import com.github.tanxinzheng.jwt.support.JwtUtils;
import com.github.tanxinzheng.jwt.support.TokenType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tanxinzheng on 17/8/19.
 */
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {


    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    JwtConfigProperties jwtConfigProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(jwtConfigProperties.getTokenHeaderName());
        if (authHeader != null && authHeader.startsWith(TokenType.BEARER.getCode())) {
            String authToken = authHeader.substring(TokenType.BEARER.getCode().length() + 1);// The part after "Bearer "
            String username = jwtUtils.getUsernameByToken(authToken);
            log.info("checking username:{}", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                CurrentLoginUser currentLoginUser = (CurrentLoginUser) userDetailsService.loadUserByUsername(username);
                if (jwtUtils.validateToken(authToken, currentLoginUser)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(currentLoginUser, currentLoginUser.getPassword(), currentLoginUser.getAuthorities());
                    authentication.setDetails(currentLoginUser);
                    log.info("authenticated user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
