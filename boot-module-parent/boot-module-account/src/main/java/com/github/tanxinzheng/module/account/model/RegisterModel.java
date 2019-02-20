package com.github.tanxinzheng.module.account.model;

import com.github.tanxinzheng.framework.validator.PhoneValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by Jeng on 2016/1/6.
 */
public @Data
class RegisterModel implements Serializable {

    /**
     * 注册类型：1-手机注册，2-邮箱注册
     */
    @NotBlank(message = "注册类型为必填项")
    private String type;
    @NotBlank(message = "用户名为必填项")
    private String username;
    @Email(message = "请输入正确格式的邮箱账号")
    private String email;

    private String nickname;
    @Pattern(regexp = PhoneValidator.PATTERN, message = "请输入正确格式的手机号码")
    private String phoneNumber;
    @NotBlank(message = "密码为必填项")
    private String password;
    @NotBlank(message = "验证码为必填项")
    private String code;

}
