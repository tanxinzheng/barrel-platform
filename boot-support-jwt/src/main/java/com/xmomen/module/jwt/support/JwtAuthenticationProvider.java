package com.xmomen.module.jwt.support;

import com.xmomen.module.jwt.JwtConfigProperties;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by tanxinzheng on 17/8/19.
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {

    public JwtAuthenticationProvider(JwtLoadService jwtLoadService,
                                     JwtTokenService jwtTokenService,
                                     JwtConfigProperties jwtConfigProperties) {
        this.jwtConfigProperties = jwtConfigProperties;
        this.jwtTokenService = jwtTokenService;
        this.jwtLoadService = jwtLoadService;
    }

    private JwtTokenService jwtTokenService;

    private JwtConfigProperties jwtConfigProperties;

    private JwtLoadService jwtLoadService;


    @Override
    public JwtAuthenticationToken authenticate(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        // 根据用户名获取用户信息
        JwtUserDetails userDetails = jwtLoadService.loadUserDetail(username);
        if(userDetails == null){
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 验证用户名和密码是否正确
        if(!jwtLoadService.matchPassword(password, userDetails)){
            throw new BadCredentialsException("用户名或密码错误");
        }
        // 生成token，refreshToken
        String token = JwtUtils.createToken(userDetails.getUsername(),
                jwtConfigProperties.getIssuer(),
                jwtConfigProperties.getSecret(),
                jwtConfigProperties.getExpiration());
        String refreshToken = JwtUtils.createToken(userDetails.getUsername(),
                jwtConfigProperties.getIssuer(),
                jwtConfigProperties.getSecret(),
                jwtConfigProperties.getExpiration());
        userDetails.setToken(token);
        userDetails.setRefreshToken(refreshToken);
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(
                userDetails.getUsername(),
                token,
                refreshToken,
                userDetails,
                userDetails.getAuthorities());
        jwtTokenService.setAuthentication(jwtAuthenticationToken);
        return jwtAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
