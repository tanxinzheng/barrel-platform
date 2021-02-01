package com.github.tanxinzheng.module.auth.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.github.tanxinzheng.framework.constant.TokenType;
import com.github.tanxinzheng.framework.exception.BusinessException;
import com.github.tanxinzheng.framework.model.Result;
import com.github.tanxinzheng.framework.secure.config.SecureProperties;
import com.github.tanxinzheng.framework.secure.domain.AuthToken;
import com.github.tanxinzheng.framework.secure.domain.AuthUser;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.framework.utils.PasswordHelper;
import com.github.tanxinzheng.framework.utils.UUIDGenerator;
import com.github.tanxinzheng.framework.web.config.WebProperties;
import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import com.github.tanxinzheng.module.auth.domain.dto.LoginRequest;
import com.github.tanxinzheng.module.auth.mapper.AuthMapper;
import com.github.tanxinzheng.module.system.feign.ISystemClient;
import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Api(tags = "认证接口")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private static final String ACCOUNT_AUTH_ERROR_COUNT = "COUNTER:ACCOUNT_AUTH_ERROR:";

    private static final int ACCOUNT_AUTH_ERROR_COUNT_TIMEOUT = 10;

    @Resource
    SecureProperties secureProperties;
    @Resource
    WebProperties webProperties;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    AuthMapper authMapper;

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
        if(webProperties.getVerification() != null && webProperties.getVerification().isEnable()){
            AssertValid.notBlank(loginRequest.getValidCode(), "请输入验证码");
            String code = (String) redisTemplate.opsForValue().get("login:captcha:" + loginRequest.getUsername());
            AssertValid.isTrue(loginRequest.getValidCode().equalsIgnoreCase(code), "输入的验证码错误，请重新输入");
        }
        String errorCountKey = ACCOUNT_AUTH_ERROR_COUNT + loginRequest.getUsername();
        Integer count = (Integer) redisTemplate.opsForValue().get(errorCountKey);
        AssertValid.isFalse(count != null && count > 5, "认证错误次数已超过5次，请{}分钟后重试。", ACCOUNT_AUTH_ERROR_COUNT_TIMEOUT);
        Result<AuthUser> result = userApiService.getUserByUsername(loginRequest.getUsername());
        AssertValid.isTrue(result.isSuccess(), result.getMessage());
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
        authToken.setExpiresIn(secureProperties.getExpiration());
        loginSuccessHandler(authUser, authToken);
        return authToken;
    }

    /**
     * 用户退出登录
     * @return
     */
    @ApiOperation(value = "用户退出登录")
    @PostMapping(value = "/logout")
    public void logout(HttpServletRequest request) {
        String accessToken = request.getHeader(secureProperties.getTokenHeaderName());
        redisTemplate.delete(secureProperties.getTokenHeaderName() + ":" + accessToken);
    }

    /**
     * 获取验证码图片
     * @return
     */
    @ApiOperation(value = "获取验证码")
    @GetMapping(value = "/captcha")
    public void captcha(@RequestParam(value = "username") String username, HttpServletResponse response) {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(100,40);
        String code = captcha.getCode();
        redisTemplate.opsForValue().set("login:captcha:" + username, code, 1, TimeUnit.MINUTES);
        try {
            OutputStream os = response.getOutputStream(); // 创建输出流
            os.write(captcha.getImageBytes());
            os.flush();
            os.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    private void loginSuccessHandler(AuthUser authUser, AuthToken authToken){
        CurrentLoginUser currentLoginUser = new CurrentLoginUser();
        currentLoginUser.setAvatar(authUser.getAvatar());
        currentLoginUser.setEmail(authUser.getEmail());
        currentLoginUser.setId(authUser.getId());
        currentLoginUser.setUsername(authUser.getUsername());
        currentLoginUser.setName(authUser.getNickname());
        currentLoginUser.setPhone(authUser.getPhoneNumber());
        if(CollectionUtils.isNotEmpty(authUser.getRoles())){
            currentLoginUser.setRoles(Sets.newHashSet(authUser.getRoles()));
        }else{
            currentLoginUser.setRoles(Sets.newHashSet());
        }
        redisTemplate.opsForValue().set(secureProperties.getTokenHeaderName() + ":" + authToken.getAccessToken(), currentLoginUser, 3, TimeUnit.HOURS);
    }

}
