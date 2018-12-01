package com.github.tanxinzheng.module.account.service.impl;

import com.github.tanxinzheng.framework.exception.BusinessException;
import com.github.tanxinzheng.framework.utils.UUIDGenerator;
import com.github.tanxinzheng.framework.web.authentication.CurrentAccountService;
import com.github.tanxinzheng.module.account.model.AccountDetail;
import com.github.tanxinzheng.module.account.service.AccountService;
import com.sun.xml.internal.ws.api.message.Attachment;
import com.xmomen.module.fss.model.FileStorageInfo;
import com.xmomen.module.fss.model.FileStorageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;

/**
 * Created by tanxinzheng on 2018/12/1.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    CurrentAccountService currentAccountService;

    /**
     * 查询账户详情
     *
     * @return
     */
    @Override
    public AccountDetail getAccountDetail() {
        return null;
    }

    /**
     * 更新账户基本信息
     *
     * @param accountDetail
     */
    @Transactional
    @Override
    public void updateAccountDetail(AccountDetail accountDetail) {

    }

    /**
     * 绑定手机
     *
     * @param phone
     */
    @Override
    public void bindPhone(String phone) {

    }

    /**
     * 绑定Email
     *
     * @param email
     */
    @Override
    public void bindEmail(String email) {

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
    public void updatePassword(String oldPassword, String newPassword) {

    }

    /**
     * 更新头像
     *
     * @param file
     */
    @Override
    public void updateAvatar(MultipartFile file) {
//        if(file.isEmpty()){
//            throw new IllegalArgumentException("请选择有效的图片");
//        }
//        FileStorageInfo fileStorageInfo = new FileStorageInfo(multipartFile);
//        FileStorageResult fileStorageResult = fileStoreService.newFile(fileStorageInfo);
//        if(!fileStorageResult.isSuccess()){
//            throw new BusinessException("图片上传失败");
//        }
//        String fileKey = UUIDGenerator.getInstance().getUUID();
//        Attachment attachment = new Attachment();
//        attachment.setAttachmentGroup(AttachmentGroupEnmu.USER_AVATAR.name());
//        attachment.setAttachmentKey(fileKey);
//        attachment.setAttachmentPath(fileStorageResult.getStoragePath());
//        attachment.setAttachmentSize(multipartFile.getSize());
//        attachment.setUploadTime(new Date());
//        attachment.setUploadUserId(userId);
//        attachment.setAttachmentSuffix(multipartFile.getContentType());
//        attachment.setOriginName("avatar.png");
//        attachment.setIsPrivate(false);
//        attachmentService.createAttachment(attachment);
//        User user = getOneUser(userId);
//        if(user != null && StringUtils.isNotBlank(user.getAvatar())){
//            // 删除旧头像
//            attachmentService.deleteAttachmentByKey(user.getAvatar());
//        }
//        User updateUser = new User();
//        updateUser.setId(userId);
//        updateUser.setAvatar(fileKey);
//        updateUser(updateUser);
    }

    /**
     * 查询账户拥有的角色
     *
     * @return
     */
    @Override
    public Set<String> findRoles() {
        return null;
    }

    /**
     * 查询账户拥有的权限资源
     *
     * @return
     */
    @Override
    public Set<String> findPermissions() {
        return null;
    }
}
