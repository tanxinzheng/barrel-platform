package com.github.tanxinzheng.jwt.controller;

import com.github.tanxinzheng.jwt.controller.dto.LoginRequest;
import com.github.tanxinzheng.jwt.controller.dto.LoginResponse;
import com.github.tanxinzheng.jwt.service.AuthManager;
import com.github.tanxinzheng.jwt.support.JwtUser;
import com.github.tanxinzheng.jwt.support.JwtUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Resource
    AuthManager authManager;

    @Resource
    JwtUtils jwtUtils;

    /**
     * 用户登录
     * @param loginRequest
     * @return
     */
    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录")
    public LoginResponse login(@RequestBody @Valid @NotNull LoginRequest loginRequest){
        JwtUser jwtUser = authManager.findUserByUsername(loginRequest.getUsername());
        if(jwtUser == null){
            throw new UsernameNotFoundException("该用户名未注册");
        }
        if (!jwtUser.getPassword().equals(loginRequest.getPassword())){
            throw new BadCredentialsException("用户名或密码不正确");
        }
        String accessToken = jwtUtils.createToken(jwtUser.getUsername());
        String refreshToken = jwtUtils.createRefreshToken(jwtUser.getUsername());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(jwtUser.getUsername());
        loginResponse.setAccessToken(accessToken);
        loginResponse.setRefreshToken(refreshToken);
        return loginResponse;
    }
}
