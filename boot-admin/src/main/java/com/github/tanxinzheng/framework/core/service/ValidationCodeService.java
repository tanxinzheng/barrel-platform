package com.github.tanxinzheng.framework.core.service;

/**
 * Created by tanxinzheng on 17/8/7.
 */
public interface ValidationCodeService {

    /**
     * 发送验证码，支持手机号码或邮件格式接收人
     * @param receiver
     */
    boolean sendCode(String receiver);

    /**
     * 校验验证码
     * @param receiver
     * @param code
     * @return
     */
    boolean validateCode(String receiver, String code);

    /**
     * 清除验证码
     * @param receiver
     */
    boolean cleanCode(String receiver);

}
