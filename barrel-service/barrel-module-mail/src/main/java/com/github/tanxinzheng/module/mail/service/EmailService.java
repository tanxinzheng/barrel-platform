package com.github.tanxinzheng.module.mail.service;

import com.github.tanxinzheng.module.mail.model.EmailModel;

/**
 * Created by tanxinzheng on 18/5/13.
 */
public interface EmailService {

    /**
     * 发送邮件
     * @param emailModel
     */
    void sendEmail(EmailModel emailModel);

}
