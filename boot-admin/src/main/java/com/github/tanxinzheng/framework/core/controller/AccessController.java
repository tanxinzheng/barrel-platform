package com.github.tanxinzheng.framework.core.controller;

import com.github.tanxinzheng.framework.utils.UUIDGenerator;
import com.github.tanxinzheng.framework.validator.PhoneValidator;
import com.github.tanxinzheng.module.verification.service.VerificationCodeService;
import com.xmomen.module.authorization.model.User;
import com.xmomen.module.authorization.model.UserModel;
import com.xmomen.module.authorization.service.UserService;
import com.github.tanxinzheng.framework.core.model.Register;
import com.github.tanxinzheng.framework.core.service.AccountService;
import com.github.tanxinzheng.framework.utils.PasswordHelper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 重置密码控制器
 */
@RestController
@RequestMapping(value = "/access")
@Slf4j
public class AccessController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Autowired
    VerificationCodeService verificationCodeService;

    public static final int FIND_TYPE_PHONE = 1;
    public static final int FIND_TYPE_EMAIL = 2;

    /**
     * 用户注册
     * @param register
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void ajaxRegister(@RequestBody @Valid Register register) {
        accountService.register(register);
    }


    /**
     * 找回密码
     * @param type      类型：1-手机，2-邮箱
     * @param receiver  手机号码或邮箱
     * @param password  新密码
     * @param code      验证码
     * @return
     */
    @RequestMapping(value = "/find_password", method = RequestMethod.PUT)
    @ApiOperation(value = "找回密码")
    public void findPassword(@RequestParam(value = "type") Integer type,
                                  @RequestParam(value = "receiver") String receiver,
                                  @RequestParam(value = "password") String password,
                                  @RequestParam(value = "code") String code) {
        Assert.isTrue(type.equals(FIND_TYPE_EMAIL) || type.equals(FIND_TYPE_PHONE), "找回方式仅支持：1-邮箱找回，2-手机找回");
        UserModel userModel = null;
        if(type.equals(FIND_TYPE_EMAIL)){
            Assert.isTrue(EmailValidator.getInstance().isValid(receiver), "请输入正确格式的邮箱");
            userModel = userService.getOneUserModelByUsername(receiver);
            Assert.notNull(userModel, "该邮箱未注册");
        }else if(type.equals(FIND_TYPE_PHONE)){
            Assert.isTrue(PhoneValidator.getInstance().isValid(receiver), "请输入正确格式的手机号码");
            userModel = userService.getOneUserModelByUsername(receiver);
            Assert.notNull(userModel, "该手机号码未注册");
        }else {
            Assert.notNull(userModel, "仅支持邮箱，手机号码方式找回密码");
        }
        Assert.isTrue(verificationCodeService.checkCode(receiver, code), "请输入有效的验证码");
        String newSalt = UUIDGenerator.getInstance().getUUID();
        String newPassword = PasswordHelper.encryptPassword(password, newSalt);
        User user = new User();
        user.setSalt(newSalt);
        user.setPassword(newPassword);
        user.setId(userModel.getId());
        userService.updateUser(user);
        verificationCodeService.cleanCode(receiver);
    }


    /**
     * 发送验证码
     * @param type      类型：1-手机，2-邮箱
     * @param receiver  手机号码或邮箱
     */
    @ApiOperation(value = "发送验证码")
    @RequestMapping(value = "/code", method = RequestMethod.POST)
    @ResponseBody
    public void setValidateCode(@RequestParam(value = "type") Integer type,
                                @RequestParam(value = "receiver") String receiver){
        Assert.isTrue(type.equals(FIND_TYPE_EMAIL) ||
                type.equals(FIND_TYPE_PHONE), "找回方式仅支持：1-邮箱找回，2-手机找回");
        if(type.equals(FIND_TYPE_PHONE)){
            Assert.isTrue(PhoneValidator.getInstance().isValid(receiver), "请输入正确格式的手机号码");
            verificationCodeService.sendCode(receiver);
        }else if(type.equals(FIND_TYPE_EMAIL)){
            Assert.isTrue(EmailValidator.getInstance().isValid(receiver), "请输入正确格式的邮箱");
            // TODO 待适配邮件接口服务
        }
    }



}