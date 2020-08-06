package com.github.tanxinzheng.module.account.service;

/**
 * 验证码服务接口
 * Created by tanxinzheng on 17/8/7.
 */
public interface VerificationCodeService {

    /**
     * 发送验证码，支持手机号码或邮件格式接收人
     * @param receiver
     * @return
     */
    boolean sendCode(String receiver);

    /**
     * 校验验证码
     * @param receiver
     * @param code
     * @return
     */
    boolean checkCode(String receiver, String code);

    /**
     * 清除验证码
     * @param receiver
     * @return
     */
    boolean cleanCode(String receiver);

}
