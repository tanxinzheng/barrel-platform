package com.github.tanxinzheng.framework.core.controller;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.core.model.AccountModel;
import com.github.tanxinzheng.framework.core.service.AccountService;
import com.github.tanxinzheng.framework.logger.ActionLog;
import com.github.tanxinzheng.framework.utils.PasswordHelper;
import com.github.tanxinzheng.framework.utils.UUIDGenerator;
import com.github.tanxinzheng.framework.validator.PhoneValidator;
import com.github.tanxinzheng.framework.web.authentication.CurrentAccountService;
import com.github.tanxinzheng.module.verification.service.VerificationCodeService;
import com.xmomen.module.authorization.model.User;
import com.xmomen.module.authorization.model.UserModel;
import com.xmomen.module.authorization.service.UserService;
import com.xmomen.module.logger.LogModel;
import com.xmomen.module.logger.model.ActionLogQuery;
import com.xmomen.module.logger.service.LoggerService;
import com.github.tanxinzheng.module.notification.model.NotificationDataState;
import com.github.tanxinzheng.module.notification.model.NotificationModel;
import com.github.tanxinzheng.module.notification.model.NotificationQuery;
import com.github.tanxinzheng.module.notification.model.NotificationStateCount;
import com.github.tanxinzheng.module.notification.service.NotificationReceiveService;
import com.github.tanxinzheng.module.notification.service.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * Created by Jeng on 2016/1/5.
 */
@RestController
@Api(value = "当前用户相关信息", description = "当前用户简要信息，权限等相关接口")
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    CurrentAccountService currentAccountService;

    @Autowired
    UserService userService;

    @Autowired
    VerificationCodeService verificationCodeService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    NotificationReceiveService notificationReceiveService;

    @Autowired
    LoggerService loggerService;

    /**
     * 查询当前用户简要信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户资料信息")
    @ActionLog(actionName = "查询当前用户资料信息")
    public AccountModel accountSetting(){
        return accountService.getCurrentAccount();
    }

    /**
     * 查询当前用户通知
     * @return
     */
    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户资料信息")
    @ActionLog(actionName = "查询当前用户资料信息")
    public Page<NotificationModel> getNotificationPage(NotificationQuery notificationQuery){
        if(notificationQuery == null){
            notificationQuery = new NotificationQuery();
        }
        notificationQuery.setUserId(currentAccountService.getAccountId());
        return notificationReceiveService.selectNotification(notificationQuery);
    }

    /**
     * 查询当前用户通知
     * @return
     */
    @RequestMapping(value = "/notification/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户资料信息")
    @ActionLog(actionName = "查询当前用户资料信息")
    public NotificationModel getNotification(@PathVariable(value = "id") String id){
        return notificationReceiveService.selectOneNotificationModel(id);
    }

    /**
     * 查询当前用户通知
     * @return
     */
    @RequestMapping(value = "/notification/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户资料信息")
    @ActionLog(actionName = "查询当前用户资料信息")
    public List<NotificationStateCount> notificationCount(NotificationQuery notificationQuery){
        if(notificationQuery == null){
            notificationQuery = new NotificationQuery();
        }
        notificationQuery.setUserId(currentAccountService.getAccountId());
        return notificationService.countNotificationState(notificationQuery);
    }

    /**
     * 查询当前用户通知
     * @return
     */
    @RequestMapping(value = "/notification/count/unread", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户资料信息")
    @ActionLog(actionName = "查询当前用户资料信息")
    public NotificationStateCount countUnRead(NotificationQuery notificationQuery){
        if(notificationQuery == null){
            notificationQuery = new NotificationQuery();
        }
        notificationQuery.setUserId(currentAccountService.getAccountId());
        List<NotificationStateCount> list = notificationService.countNotificationState(notificationQuery);
        if(CollectionUtils.isEmpty(list)){
            return new NotificationStateCount();
        }
        Optional<NotificationStateCount> stateCount = list.stream().filter(notificationStateCount -> {
            return NotificationDataState.UNREAD.name().equals(notificationStateCount.getDataState());
        }).findFirst();
        if(stateCount.isPresent()){
            return stateCount.get();
        }
        return new NotificationStateCount();
    }

    /**
     * 当前用户权限
     * @return
     */
    @RequestMapping(value = "/authorities", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户权限")
    @ActionLog(actionName = "查询当前用户权限")
    public Map getAccountAuthorities(){
        Set<String> roles = currentAccountService.getRoles();
        Set<String> permissions = currentAccountService.getPermissions();
        Map rolesMap = new HashMap();
        rolesMap.put("roles", roles);
        rolesMap.put("permissions", permissions);
        return rolesMap;
    }

    /**
     * 当前用户修改密码
     * @param oldPassword
     * @param password
     */
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    @ApiOperation(value = "当前用户修改密码")
    @ActionLog(actionName = "当前用户修改密码")
    public void updatePassword(@RequestParam(value = "oldPassword") String oldPassword,
                              @RequestParam(value = "password") String password){
        String userId = currentAccountService.getAccountId();
        UserModel userModel = userService.getOneUserModel(userId);
        Assert.notNull(userModel, "未找到当前用户帐号");
        String encryptPassword = PasswordHelper.encryptPassword(oldPassword, userModel.getSalt());
        Assert.isTrue(encryptPassword.equals(userModel.getPassword()), "输入的旧密码不正确");
        String salt = UUIDGenerator.getInstance().getUUID();
        String newEncryptPassword = PasswordHelper.encryptPassword(password, userModel.getSalt());
        User user = new User();
        user.setId(userId);
        user.setSalt(salt);
        user.setPassword(newEncryptPassword);
        userService.updateUser(user);
    }

    /**
     * 绑定手机，邮箱
     * @param type  1-手机，2-邮箱
     * @param receiver
     */
    @RequestMapping(value = "/bind", method = RequestMethod.PUT)
    @ApiOperation(value = "绑定手机、邮箱")
    @ActionLog(actionName = "绑定手机、邮箱")
    public void bind(@RequestParam(value = "type") Integer type,
                     @RequestParam(value = "receiver") String receiver,
                     @RequestParam(value = "code") String code){
        Assert.isTrue(type.equals(AccessController.FIND_TYPE_EMAIL) || type.equals(AccessController.FIND_TYPE_PHONE), "找回方式仅支持：1-邮箱找回，2-手机找回");
        verificationCodeService.checkCode(receiver, code);
        UserModel userModel;
        User user = new User();
        if(type.equals(AccessController.FIND_TYPE_EMAIL)){
            org.springframework.util.Assert.isTrue(EmailValidator.getInstance().isValid(receiver), "请输入正确格式的邮箱");
            userModel = userService.getOneUserModelByUsername(receiver);
            Assert.isNull(userModel, "该邮箱已被绑定");
            user.setEmail(receiver);
        }else if(type.equals(AccessController.FIND_TYPE_PHONE)){
            org.springframework.util.Assert.isTrue(PhoneValidator.getInstance().isValid(receiver), "请输入正确格式的手机号码");
            userModel = userService.getOneUserModelByUsername(receiver);
            Assert.isNull(userModel, "该手机号码已被绑定");
            user.setPhoneNumber(receiver);
        }
        user.setId(currentAccountService.getAccountId());
        userService.updateUser(user);
        verificationCodeService.cleanCode(receiver);
    }

    /**
     * 更换头像
     * @param file
     */
    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    @ApiOperation(value = "更换头像")
    @ActionLog(actionName = "更换头像")
    public void updateAvatar(@RequestPart(value = "file") MultipartFile file) {
        if(file.isEmpty()){
            return;
        }
        String userId = currentAccountService.getAccountId();
        userService.updateAvatar(userId, file);
    }


    /**
     * 当前用户操作日志
     * @return
     */
    @RequestMapping(value = "/action_log", method = RequestMethod.GET)
    @ApiOperation(value = "当前用户操作日志")
    public Page<LogModel> getAccountActionLog(ActionLogQuery actionLogQuery){
        actionLogQuery.setDefaultPage();
        actionLogQuery.setUserId(currentAccountService.getAccountId());
        return loggerService.getActionLogPage(actionLogQuery);
    }


}
