package com.github.tanxinzheng.module.verification.service;

/**
 * Created by tanxinzheng on 2018/11/20.
 */
public interface SMSService {

    /**
     * 发送短信
     * @param phone
     * @param content
     * @return
     */
    boolean sendMessage(String phone, String content);
}
