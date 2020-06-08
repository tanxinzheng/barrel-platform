package com.github.tanxinzheng.module.account.controller;

import com.github.tanxinzheng.module.account.model.AccountModel;
import com.github.tanxinzheng.module.account.service.AccountService;
import com.github.tanxinzheng.module.verification.service.VerificationCodeService;
import com.github.tanxinzheng.web.annotation.LoginUser;
import com.github.tanxinzheng.web.model.CurrentLoginUser;
import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

/**
 * Created by Jeng on 2016/1/5.
 */
@RestController
@Api(tags = "个人中心")
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    VerificationCodeService verificationCodeService;

    /**
     * 查询当前用户简要信息
     * @param loginUser
     * @return
     */
    @GetMapping
    @ApiOperation(value = "个人资料")
    public AccountModel accountSetting(@LoginUser CurrentLoginUser loginUser){
        AccountModel accountModel = new AccountModel();
        accountModel.setAvatar(loginUser.getAvatar());
        accountModel.setEmail(loginUser.getEmail());
        accountModel.setId(loginUser.getId());
        accountModel.setPhone(loginUser.getPhone());
        return accountModel;
    }

    /**
     * 当前用户权限
     * @return
     */
    @GetMapping(value = "/authorities")
    @ApiOperation(value = "我的权限")
    public Set<String> getAccountAuthorities(@LoginUser CurrentLoginUser loginUser){
        Set<String> roles = loginUser.getRoles();
        if(CollectionUtils.isEmpty(roles)){
            roles = Sets.newHashSet();
        }
        return roles;
    }

    /**
     * 当前用户修改密码
     * @param loginUser
     * @param oldPassword
     * @param password
     */
    @PutMapping(value = "/password")
    @ApiOperation(value = "修改密码")
    public void updatePassword(@LoginUser CurrentLoginUser loginUser,
                               @RequestParam(value = "oldPassword") String oldPassword,
                              @RequestParam(value = "password") String password){
        accountService.updatePassword(loginUser.getId(), oldPassword, password);
    }

    /**
     * 绑定手机，邮箱
     * @param loginUser
     * @param type
     * @param receiver
     * @param code
     */
    @PutMapping(value = "/bind")
    @ApiOperation(value = "绑定手机、邮箱")
    public void bind(@LoginUser CurrentLoginUser loginUser,
                     @RequestParam(value = "type") Integer type,
                     @RequestParam(value = "receiver") String receiver,
                     @RequestParam(value = "code") String code){
        Assert.isTrue(type.equals(1) || type.equals(2), "仅支持的两种绑定类型：1-邮箱，2-手机");
        verificationCodeService.checkCode(receiver, code);
        switch (type){
            case 1:
                accountService.bindEmail(loginUser.getId(), receiver);
                break;
            case 2:
                accountService.bindPhone(loginUser.getId(), receiver);
                break;
            default:
                throw new IllegalArgumentException("仅支持的两种绑定类型：1-邮箱，2-手机");
        }
    }

    /**
     * 更换头像
     * @param loginUser
     * @param file
     */
    @PostMapping(value = "/avatar")
    @ApiOperation(value = "更换头像")
    public void updateAvatar(@LoginUser CurrentLoginUser loginUser,
                             @RequestPart(value = "file") MultipartFile file) {
        accountService.updateAvatar(loginUser.getId(), file);
    }


}
