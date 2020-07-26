package com.github.tanxinzheng.module.system.controller;

import com.github.tanxinzheng.framework.constant.JwtConfigProperties;
import com.github.tanxinzheng.framework.constant.TokenType;
import com.github.tanxinzheng.framework.exception.BusinessException;
import com.github.tanxinzheng.framework.model.Result;
import com.github.tanxinzheng.framework.secure.domain.AuthToken;
import com.github.tanxinzheng.framework.secure.domain.AuthUser;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.framework.utils.JwtUtils;
import com.github.tanxinzheng.framework.utils.PasswordHelper;
import com.github.tanxinzheng.framework.utils.UUIDGenerator;
import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import com.github.tanxinzheng.module.system.domain.dto.LoginRequest;
import com.github.tanxinzheng.module.system.feign.ISystemClient;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
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
    ISystemClient userApiService;

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
        Result<AuthUser> result = userApiService.getUserByUsername(loginRequest.getUsername());
        if(!result.isSuccess()){
            throw new BusinessException(result.getMessage());
        }
        AuthUser authUser = result.getData();
        AssertValid.notNull(authUser, "该用户名未注册");
        AssertValid.isTrue(!authUser.isDisable(), "该用户已被禁用，若要启用，请联系管理员。");
        String rawPassword = PasswordHelper.encryptPassword(loginRequest.getPassword(), authUser.getSalt());
        if (!authUser.getPassword().equals(rawPassword)){
            redisTemplate.opsForValue().increment(ACCOUNT_AUTH_ERROR_COUNT + loginRequest.getUsername(), 1);
            // 设置认证失败错误次数缓存过期时间
            redisTemplate.expire(ACCOUNT_AUTH_ERROR_COUNT + loginRequest.getUsername(), ACCOUNT_AUTH_ERROR_COUNT_TIMEOUT, TimeUnit.MINUTES);
            throw new BusinessException("用户名或密码不正确");
        }
        Result<List<String>> roles = userApiService.getRoles(authUser.getId());
        if(roles.isSuccess()){
            authUser.setRoles(roles.getData());
        }
        String accessToken = UUIDGenerator.getInstance().getUUID();
        AuthToken authToken = new AuthToken();
        authToken.setAccessToken(accessToken);
        authToken.setAvatar(authUser.getAvatar());
        authToken.setUserId(authUser.getId());
        authToken.setUsername(authUser.getUsername());
        authToken.setTokenType(TokenType.BEARER.getCode());
        authToken.setExpiresIn(jwtConfigProperties.getExpiration());
        loginSuccessHandler(authUser, authToken);
        return authToken;
    }

    private void loginSuccessHandler(AuthUser authUser, AuthToken authToken){
        CurrentLoginUser currentLoginUser = new CurrentLoginUser();
        currentLoginUser.setAvatar(authUser.getAvatar());
        currentLoginUser.setEmail(authUser.getEmail());
        currentLoginUser.setId(authUser.getId());
        currentLoginUser.setUsername(authUser.getUsername());
        currentLoginUser.setName(authUser.getNickname());
        currentLoginUser.setPhone(authUser.getPhoneNumber());
        currentLoginUser.setRoles(Sets.newHashSet(authUser.getRoles()));
        redisTemplate.opsForValue().set(jwtConfigProperties.getTokenHeaderName() + ":" + authToken.getAccessToken(), currentLoginUser, 3, TimeUnit.HOURS);
    }

}
