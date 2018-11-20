package com.github.tanxinzheng.module.verification.service;

import com.github.tanxinzheng.module.verification.service.impl.VerificationCodeServiceImpl;
import com.github.tanxinzheng.test.TestAppService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;

/**
 * Created by tanxinzheng on 2018/11/20.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VerificationCodeServiceTest extends TestAppService {

    @Autowired
    VerificationCodeService verificationCodeService;

    @Autowired
    CacheManager cacheManager;

    String code = "15088880000";

    @Test
    public void test0sendCode() throws Exception {
        boolean isSent = verificationCodeService.sendCode(code);
        Assert.isTrue(isSent, "验证不通过，发送验证码");
    }

    @Test
    public void test1checkCode() throws Exception {
        Cache cache = cacheManager.getCache(VerificationCodeServiceImpl.VALIDATE_CODE_CACHE_NAME);
        String cacheCode = cache.get(VerificationCodeServiceImpl.VALIDATE_CODE_CACHE_NAME + ":" + code, String.class);
        boolean isSent = verificationCodeService.checkCode(code, cacheCode);
        Assert.isTrue(isSent, "验证不通过，发送验证码");
    }

    @Test
    public void test2cleanCode() throws Exception {
        boolean isSent = verificationCodeService.cleanCode(code);
        Assert.isTrue(isSent, "验证不通过，发送验证码");
    }

}