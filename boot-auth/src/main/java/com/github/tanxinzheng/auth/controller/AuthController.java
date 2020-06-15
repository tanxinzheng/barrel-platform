package com.github.tanxinzheng.auth.controller;

import com.github.tanxinzheng.auth.domain.dto.LoginRequest;
import com.github.tanxinzheng.auth.utils.JwtUtils;
import com.github.tanxinzheng.framework.constant.JwtConfigProperties;
import com.github.tanxinzheng.framework.constant.TokenType;
import com.github.tanxinzheng.framework.exception.AuthException;
import com.github.tanxinzheng.framework.secure.domain.AuthToken;
import com.github.tanxinzheng.framework.secure.domain.AuthUser;
import com.github.tanxinzheng.framework.utils.PasswordHelper;
import com.github.tanxinzheng.module.feign.UserClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

@Api(tags = "认证接口")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private static final String ACCOUNT_AUTH_ERROR_COUNT = "COUNTER:ACCOUNT_AUTH_ERROR:";

    @Resource
    JwtConfigProperties jwtConfigProperties;

    @Resource
    UserClient userApiService;

    @Resource
    RedisTemplate redisTemplate;

    /**
     * 用户登录
     * @param loginRequest
     * @return
     */
    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录")
    public AuthToken login(@RequestBody @Validated @NotNull LoginRequest loginRequest){
        String errorCountKey = ACCOUNT_AUTH_ERROR_COUNT + loginRequest.getUsername();
        Long count = (Long) redisTemplate.opsForValue().get(errorCountKey);
        if(count > 5l){
            throw new AuthException("认证错误次数已超过5次，请稍后重试。");
        }
        AuthUser authUser = userApiService.getUserByUsername(loginRequest.getUsername());
        if(authUser == null){
            throw new AuthException("该用户名未注册");
        }
        String rawPassword = PasswordHelper.encryptPassword(loginRequest.getPassword(), authUser.getSalt());
        if (!authUser.getPassword().equals(rawPassword)){
            count = redisTemplate.opsForValue().increment(ACCOUNT_AUTH_ERROR_COUNT + loginRequest.getUsername(), 1);
            // 设置认证失败错误次数缓存过期时间
            redisTemplate.expire(ACCOUNT_AUTH_ERROR_COUNT + loginRequest.getUsername(), 10, TimeUnit.MINUTES);
            throw new AuthException("用户名或密码不正确");
        }
        String accessToken = JwtUtils.createToken(authUser.getUsername(),
                jwtConfigProperties.getSecret(),
                jwtConfigProperties.getExpiration(),
                jwtConfigProperties.getIssuer());
        String refreshToken = JwtUtils.createToken(authUser.getUsername(),
                jwtConfigProperties.getSecret(),
                jwtConfigProperties.getRefreshTokenExpiration(),
                jwtConfigProperties.getIssuer());
        AuthToken authToken = new AuthToken();
        authToken.setAccessToken(accessToken);
        authToken.setAvatar(authUser.getAvatar());
        authToken.setRefreshToken(refreshToken);
        authToken.setUsername(authUser.getUsername());
        authToken.setTokenType(TokenType.BEARER.getCode());
//        authToken.setExpiresIn();
        return authToken;
    }


}
