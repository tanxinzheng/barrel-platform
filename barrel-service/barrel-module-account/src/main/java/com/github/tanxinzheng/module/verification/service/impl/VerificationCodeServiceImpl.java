package com.github.tanxinzheng.module.verification.service.impl;

import com.github.tanxinzheng.cache.utils.RedisUtils;
import com.github.tanxinzheng.module.verification.service.VerificationCodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by tanxinzheng on 17/8/7.
 */
@Service
@Slf4j
public class VerificationCodeServiceImpl implements VerificationCodeService {

    public static final String VALIDATE_CODE_CACHE_NAME = "VerificationCode";

    @Resource
    CacheManager cacheManager;

    /**
     * 发送验证码
     *
     * @param receiver
     */
    @Override
    public boolean sendCode(String receiver) {
        Assert.hasLength(receiver, "请输入手机号码");
        try {
            Cache cache = cacheManager.getCache(VALIDATE_CODE_CACHE_NAME);
            String code = RandomStringUtils.randomNumeric(6);
            log.debug("The validation code stored to cache , Receiver Key: {}, Cache Key: {} ", receiver, code);
            // TODO 待适配短信接口
            cache.put(getCacheKey(receiver), code);
            return Boolean.TRUE;
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IllegalArgumentException("验证码发送失败。", e);
        }
    }

    private String getCacheKey(String receiver){
        return VALIDATE_CODE_CACHE_NAME + ":" + receiver;
    }

    /**
     * 校验代码
     *
     * @param receiver
     * @param code
     * @return
     */
    @Override
    public boolean checkCode(String receiver, String code) {
        Cache cache = cacheManager.getCache(VALIDATE_CODE_CACHE_NAME);
        Assert.notNull(cache, "请输入有效的验证码");
        String cacheCode = cache.get(getCacheKey(receiver), String.class);
        Assert.notNull(cacheCode, "请输入有效的验证码");
        Assert.isTrue(cacheCode.equals(code), "验证码错误");
        return true;
    }

    /**
     * 清除验证码
     *
     * @param receiver
     */
    @Override
    public boolean cleanCode(String receiver) {
        Cache cache = cacheManager.getCache(VALIDATE_CODE_CACHE_NAME);
        if(cache == null){
            return Boolean.FALSE;
        }
        cache.put(receiver, null);
        return Boolean.TRUE;
    }
}
