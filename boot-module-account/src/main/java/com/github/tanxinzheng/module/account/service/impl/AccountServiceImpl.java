package com.github.tanxinzheng.module.account.service.impl;

import com.github.tanxinzheng.framework.exception.BusinessException;
import com.github.tanxinzheng.framework.utils.PasswordHelper;
import com.github.tanxinzheng.framework.utils.UUIDGenerator;
import com.github.tanxinzheng.framework.validator.PhoneValidator;
import com.github.tanxinzheng.framework.web.authentication.CurrentAccountService;
import com.github.tanxinzheng.module.account.model.AccountDetail;
import com.github.tanxinzheng.module.account.service.AccountService;
import com.github.tanxinzheng.module.authorization.model.User;
import com.github.tanxinzheng.module.authorization.model.UserModel;
import com.github.tanxinzheng.module.authorization.model.UserQuery;
import com.github.tanxinzheng.module.authorization.service.UserService;
import com.github.tanxinzheng.module.fss.model.FileStorageInfo;
import com.github.tanxinzheng.module.fss.model.FileStorageResult;
import com.github.tanxinzheng.module.fss.service.FileStoreService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

/**
 * Created by tanxinzheng on 2018/12/1.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    CurrentAccountService<AccountDetail> currentAccountService;

    @Autowired
    UserService userService;

    @Autowired
    FileStoreService fileStoreService;

    /**
     * 查询账户详情
     *
     * @return
     */
    @Override
    public AccountDetail getAccountDetail() {
        return currentAccountService.getAccountDetail();
    }

    /**
     * 更新账户基本信息
     *
     * @param accountDetail
     */
    @Transactional
    @Override
    public void updateNickName(AccountDetail accountDetail) {
        String userId = currentAccountService.getAccountId();
        User user = new User();
        user.setId(userId);
        user.setNickname(accountDetail.getName());
        userService.updateUser(user);
    }

    /**
     * 绑定手机
     *
     * @param phone
     */
    @Override
    public void bindPhone(String phone) {
        Assert.isTrue(PhoneValidator.getInstance().isValid(phone), "请输入正确格式的手机号码");
        String userId = currentAccountService.getAccountId();
        UserQuery userQuery = new UserQuery();
        userQuery.setPhone(phone);
        UserModel exitUser = userService.getOneUserModel(userQuery);
        Assert.isNull(exitUser, "该手机号码已被绑定");
        User user = new User();
        user.setId(userId);
        user.setPhoneNumber(phone);
        userService.updateUser(user);
    }

    /**
     * 绑定Email
     *
     * @param email
     */
    @Override
    public void bindEmail(String email) {
        Assert.isTrue(EmailValidator.getInstance().isValid(email), "请输入正确格式的邮箱");
        String userId = currentAccountService.getAccountId();
        UserQuery userQuery = new UserQuery();
        userQuery.setEmail(email);
        UserModel exitUser = userService.getOneUserModel(userQuery);
        Assert.isNull(exitUser, "该邮箱已被绑定");
        User user = new User();
        user.setId(userId);
        user.setEmail(email);
        userService.updateUser(user);
    }

    /**
     * 绑定微信
     *
     * @param wechatId
     */
    @Override
    public void bindWechat(String wechatId) {

    }

    /**
     * 更新密码
     *
     * @param oldPassword
     * @param newPassword
     */
    @Override
    @Transactional
    public void updatePassword(String oldPassword, String newPassword) {
        String userId = currentAccountService.getAccountId();
        User query = userService.getOneUser(userId);
        String encryptPassword = PasswordHelper.encryptPassword(oldPassword, query.getSalt());
        Assert.isTrue(encryptPassword.equals(oldPassword), "输入的旧密码不正确");
        String salt = UUIDGenerator.getInstance().getUUID();
        String newEncryptPassword = PasswordHelper.encryptPassword(newPassword, salt);
        User user = new User();
        user.setId(userId);
        user.setSalt(salt);
        user.setPassword(newEncryptPassword);
    }

    /**
     * 更新头像
     *
     * @param file
     */
    @Override
    @Transactional
    public void updateAvatar(MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalArgumentException("请选择有效的图片");
        }
        String userId = currentAccountService.getAccountId();

        User user = userService.getOneUser(userId);
        if(StringUtils.isNotBlank(user.getAvatar())){
            // 删除旧头像
            FileStorageResult info = fileStoreService.getFile(user.getAvatar());
            fileStoreService.deleteFile(info.getStoragePath());
        }
        // 保存新头像
        FileStorageInfo fileStorageInfo = new FileStorageInfo(file);
        FileStorageResult fileStorageResult = fileStoreService.newFile(fileStorageInfo);
        if(!fileStorageResult.isSuccess()){
            throw new BusinessException("图片上传失败");
        }
        String fileKey = UUIDGenerator.getInstance().getUUID();
        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setAvatar(fileKey);
        userService.updateUser(updateUser);
    }

    /**
     * 查询账户拥有的角色
     *
     * @return
     */
    @Override
    public Set<String> findRoles() {
        return currentAccountService.getRoles();
    }

    /**
     * 查询账户拥有的权限资源
     *
     * @return
     */
    @Override
    public Set<String> findPermissions() {
        return currentAccountService.getPermissions();
    }
}
