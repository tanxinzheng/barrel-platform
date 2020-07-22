package com.github.tanxinzheng.module.account.service.impl;

import com.github.tanxinzheng.framework.constant.JwtConfigProperties;
import com.github.tanxinzheng.framework.model.Result;
import com.github.tanxinzheng.framework.secure.domain.AuthUser;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.framework.utils.PasswordHelper;
import com.github.tanxinzheng.framework.utils.UUIDGenerator;
import com.github.tanxinzheng.framework.validator.PhoneValidator;
import com.github.tanxinzheng.module.account.mapper.AccountMapper;
import com.github.tanxinzheng.module.account.service.AccountService;
import com.github.tanxinzheng.module.auth.feign.IUserClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by tanxinzheng on 2018/12/1.
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    IUserClient userClient;

    @Resource
    AccountMapper accountMapper;

    @Resource
    JwtConfigProperties jwtConfigProperties;

    /**
     * 更新账户基本信息
     * @param userId
     * @param nickname
     * @return
     */
    @Transactional
    @Override
    public boolean updateNickName(String userId, String nickname) {
        return accountMapper.updateNickname(userId, nickname) > 0;
    }

    /**
     * 绑定手机
     *
     * @param phone
     */
    @Override
    public boolean bindPhone(String userId, String phone) {
        Assert.isTrue(PhoneValidator.getInstance().isValid(phone), "请输入正确格式的手机号码");
        Result<AuthUser> authUserResult = userClient.getUserByUsername(phone);
        Assert.notNull(authUserResult.getData(), "该手机号码已被绑定");
        return accountMapper.bindPhone(phone, userId) > 0;
    }

    /**
     * 绑定Email
     *
     * @param email
     */
    @Override
    public boolean bindEmail(String userId, String email) {
        Assert.isTrue(EmailValidator.getInstance().isValid(email), "请输入正确格式的邮箱");
        Result<AuthUser> authUserResult = userClient.getUserByUsername(email);
        Assert.notNull(authUserResult.getData(), "该邮箱已被绑定");
        return accountMapper.bindEmail(email, userId) > 0;
    }

    /**
     * 绑定微信
     *
     * @param wechatId
     */
    @Override
    public boolean bindWechat(String userId, String wechatId) {
        return false;
    }

    /**
     * 更新密码
     *
     * @param oldPassword
     * @param newPassword
     */
    @Override
    @Transactional
    public boolean updatePassword(String userId, String oldPassword, String newPassword) {
        AssertValid.notBlank(userId, "参数不能为空");
        Result<AuthUser> authUserResult = userClient.getUserByUserId(userId);
        AssertValid.isTrue(null != authUserResult && authUserResult.isSuccess(), "未找到该用户");
        AuthUser authUser = authUserResult.getData();
        AssertValid.notNull(authUser, "未找到该用户");
        String encryptPassword = PasswordHelper.encryptPassword(oldPassword, authUser.getSalt());
        Assert.isTrue(encryptPassword.equals(oldPassword), "输入的旧密码不正确");
        String newSalt = UUIDGenerator.getInstance().getUUID();
        String newEncryptPassword = PasswordHelper.encryptPassword(newPassword, newSalt);
        int result = accountMapper.updatePassword(userId, newSalt, newEncryptPassword);
        return result == 1;
    }

    /**
     * 更新头像
     *
     * @param file
     */
    @Override
    @Transactional
    public boolean updateAvatar(String userId, MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalArgumentException("请选择有效的图片");
        }
        Result<AuthUser> result = userClient.getUserByUserId(userId);
//        if(result.getData() != null && StringUtils.isNotBlank(result.getData().getAvatar())){
//            // 删除旧头像
//            FileStorageResult info = fileStoreService.getFile(user.getAvatar());
//            fileStoreService.deleteFile(info.getStoragePath());
//        }+
//        // 保存新头像
//        FileStorageInfo fileStorageInfo = new FileStorageInfo(file);
//        FileStorageResult fileStorageResult = fileStoreService.newFile(fileStorageInfo);
//        if(!fileStorageResult.isSuccess()){
//            throw new BusinessException("图片上传失败");
//        }
//        String fileKey = UUIDGenerator.getInstance().getUUID();
//
        return false;
    }

    private AuthUser getAuthUser(String username){
        Result<AuthUser> authUserResult = userClient.getUserByUsername(username);
        AssertValid.isTrue(null != authUserResult && authUserResult.isSuccess(), "未找到该用户");
        return authUserResult.getData();
    }

}
