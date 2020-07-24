package com.github.tanxinzheng.module.auth.controller;

import com.github.tanxinzheng.framework.constant.JwtConfigProperties;
import com.github.tanxinzheng.framework.constant.TokenType;
import com.github.tanxinzheng.framework.exception.BusinessException;
import com.github.tanxinzheng.framework.model.Result;
import com.github.tanxinzheng.framework.secure.domain.AuthToken;
import com.github.tanxinzheng.framework.secure.domain.AuthUser;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.framework.utils.JwtUtils;
import com.github.tanxinzheng.framework.utils.PasswordHelper;
import com.github.tanxinzheng.module.auth.domain.dto.LoginRequest;
import com.github.tanxinzheng.module.auth.feign.IUserClient;
import com.github.tanxinzheng.module.auth.mapper.AuthMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

@Slf4j
@Api(tags = "认证接口")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private static final String ACCOUNT_AUTH_ERROR_COUNT = "COUNTER:ACCOUNT_AUTH_ERROR:";

    private static final int ACCOUNT_AUTH_ERROR_COUNT_TIMEOUT = 10;

    @Resource
    JwtConfigProperties jwtConfigProperties;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    IUserClient userApiService;

    @Resource
    AuthMapper authMapper;


    /**
     * 用户登录
     * @param loginRequest
     * @return
     */
    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    public AuthToken login(@RequestBody @Validated @NotNull LoginRequest loginRequest){
        String errorCountKey = ACCOUNT_AUTH_ERROR_COUNT + loginRequest.getUsername();
        Integer count = (Integer) redisTemplate.opsForValue().get(errorCountKey);
        if(count != null && count > 5){
            throw new BusinessException("认证错误次数已超过5次，请{}分钟后重试。", ACCOUNT_AUTH_ERROR_COUNT_TIMEOUT);
        }
        AuthUser authUser = userApiService.getUserByUsername(loginRequest.getUsername());
        AssertValid.notNull(authUser, "该用户名未注册");
        AssertValid.isTrue(!authUser.isDisable(), "该用户已被禁用，若要启用，请联系管理员。");
        String rawPassword = PasswordHelper.encryptPassword(loginRequest.getPassword(), authUser.getSalt());
        if (!authUser.getPassword().equals(rawPassword)){
            redisTemplate.opsForValue().increment(ACCOUNT_AUTH_ERROR_COUNT + loginRequest.getUsername(), 1);
            // 设置认证失败错误次数缓存过期时间
            redisTemplate.expire(ACCOUNT_AUTH_ERROR_COUNT + loginRequest.getUsername(), ACCOUNT_AUTH_ERROR_COUNT_TIMEOUT, TimeUnit.MINUTES);
            throw new BusinessException("用户名或密码不正确");
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
        authToken.setExpiresIn(jwtConfigProperties.getExpiration());
        return authToken;
    }


}
