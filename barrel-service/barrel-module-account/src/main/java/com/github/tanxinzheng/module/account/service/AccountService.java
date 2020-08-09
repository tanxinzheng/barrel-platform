package com.github.tanxinzheng.module.account.service;

import com.github.tanxinzheng.module.account.model.AccountDetail;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by tanxinzheng on 2018/11/30.
 */
public interface AccountService {

    /**
     * 更新账户基本信息
     */
    boolean updateNickName(String userId, String nickname);

    /**
     * 绑定手机
     * @param phone
     */
    boolean bindPhone(String userId, String phone);

    /**
     * 绑定Email
     * @param email
     */
    boolean bindEmail(String userId, String email);

    /**
     * 绑定微信
     * @param wechatId
     */
    boolean bindWechat(String userId, String wechatId);

    /**
     * 更新密码
     * @param oldPassword
     * @param newPassword
     */
    boolean updatePassword(String userId, String oldPassword, String newPassword);

    /**
     * 更新头像
     * @param file
     */
    boolean updateAvatar(String userId, MultipartFile file);

}
