package com.github.tanxinzheng.module.account.service;

import com.github.tanxinzheng.module.account.model.AccountDetail;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by tanxinzheng on 2018/11/30.
 */
public interface AccountService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 更新账户基本信息
     */
    void updateNickName(String userId, AccountDetail accountDetail);

    /**
     * 绑定手机
     * @param phone
     */
    void bindPhone(String userId, String phone);

    /**
     * 绑定Email
     * @param email
     */
    void bindEmail(String userId, String email);

    /**
     * 绑定微信
     * @param wechatId
     */
    void bindWechat(String userId, String wechatId);

    /**
     * 更新密码
     * @param oldPassword
     * @param newPassword
     */
    void updatePassword(String userId, String oldPassword, String newPassword);

    /**
     * 更新头像
     * @param file
     */
    void updateAvatar(String userId, MultipartFile file);

}
