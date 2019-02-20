package com.github.tanxinzheng.module.account.controller;

import com.github.tanxinzheng.framework.logger.ActionLog;
import com.github.tanxinzheng.framework.web.authentication.CurrentAccountService;
import com.github.tanxinzheng.module.account.model.AccountModel;
import com.github.tanxinzheng.module.account.service.AccountService;
import com.github.tanxinzheng.module.authorization.model.User;
import com.github.tanxinzheng.module.authorization.service.UserService;
import com.github.tanxinzheng.module.verification.service.VerificationCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jeng on 2016/1/5.
 */
@RestController
@Api(value = "当前用户相关信息", description = "当前用户简要信息，权限等相关接口")
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    CurrentAccountService currentAccountService;

    @Autowired
    VerificationCodeService verificationCodeService;

    @Autowired
    UserService userService;

    /**
     * 查询当前用户简要信息
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询当前用户资料信息")
    @ActionLog(actionName = "查询当前用户资料信息")
    public AccountModel accountSetting(){
        String userId = currentAccountService.getAccountId();
        User user = userService.getOneUser(userId);
        AccountModel accountModel = new AccountModel();
        accountModel.setAvatar(user.getAvatar());
        accountModel.setEmail(user.getEmail());
        accountModel.setId(user.getId());
        accountModel.setPhone(user.getPhoneNumber());
        return accountModel;
    }

    /**
     * 当前用户权限
     * @return
     */
    @GetMapping(value = "/authorities")
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
    @PutMapping(value = "/password")
    @ApiOperation(value = "当前用户修改密码")
    @ActionLog(actionName = "当前用户修改密码")
    public void updatePassword(@RequestParam(value = "oldPassword") String oldPassword,
                              @RequestParam(value = "password") String password){
        accountService.updatePassword(oldPassword, password);
    }

    /**
     * 绑定手机，邮箱
     * @param type  1-手机，2-邮箱
     * @param receiver
     */
    @PutMapping(value = "/bind")
    @ApiOperation(value = "绑定手机、邮箱")
    @ActionLog(actionName = "绑定手机、邮箱")
    public void bind(@RequestParam(value = "type") Integer type,
                     @RequestParam(value = "receiver") String receiver,
                     @RequestParam(value = "code") String code){
        Assert.isTrue(type.equals(1) || type.equals(2), "仅支持的两种绑定类型：1-邮箱，2-手机");
        verificationCodeService.checkCode(receiver, code);
        switch (type){
            case 1:
                accountService.bindEmail(receiver);
                break;
            case 2:
                accountService.bindPhone(receiver);
                break;
            default:
                throw new IllegalArgumentException("仅支持的两种绑定类型：1-邮箱，2-手机");
        }
    }

    /**
     * 更换头像
     * @param file
     */
    @PostMapping(value = "/avatar")
    @ApiOperation(value = "更换头像")
    @ActionLog(actionName = "更换头像")
    public void updateAvatar(@RequestPart(value = "file") MultipartFile file) {
        accountService.updateAvatar(file);
    }


}
