package com.github.tanxinzheng.module.account.access.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 重置密码控制器
 */
@RestController
@Api(tags = "用户认证")
@RequestMapping(value = "/access")
@Slf4j
public class AccessController {
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    AccountService accountService;
//
//    @Autowired
//    VerificationCodeService verificationCodeService;
//
//    public static final int FIND_TYPE_EMAIL = 1;
//    public static final int FIND_TYPE_PHONE = 2;
//
//    /**
//     * 用户注册
//     * @param register
//     * @return
//     */
//    @ApiOperation(value = "用户注册")
//    @PostMapping(value = "/register")
//    public void registerModel(@RequestBody @Valid RegisterModel register) {
//        UserModel checkUser = userService.getOneUserModelByUsername(register.getUsername());
//        Assert.isNull(checkUser, "该用户名已被注册");
//        switch (register.getType()){
//            case "1":
//        }
//        if(register.getType().equals("2") && StringUtils.isNotBlank(register.getEmail())){
//            verificationCodeService.checkCode(register.getEmail(), register.getCode());
//            Assert.isTrue(EmailValidator.getInstance().isValid(register.getEmail()), "请输入正确格式的邮箱");
//            UserModel emailUser = userService.getOneUserModelByUsername(register.getEmail());
//            Assert.isNull(emailUser, "该邮箱已被注册");
//        }else if(register.getType().equals("1") && StringUtils.isNotBlank(register.getPhoneNumber())){
//            verificationCodeService.checkCode(register.getPhoneNumber(), register.getCode());
//            Assert.isTrue(PhoneValidator.getInstance().isValid(register.getPhoneNumber()), "请输入正确格式的手机号码");
//            UserModel phoneUser = userService.getOneUserModelByUsername(register.getPhoneNumber());
//            Assert.isNull(phoneUser, "该手机号码已被注册");
//        }
//        String salt = UUIDGenerator.getInstance().getUUID();
//        String encryptPassword = PasswordHelper.encryptPassword(register.getPassword(), salt);
//        UserModel userCreate = new UserModel();
//        userCreate.setEmail(register.getEmail());
//        userCreate.setPhoneNumber(register.getPhoneNumber());
//        userCreate.setCreatedTime(new Date());
//        if(StringUtils.trimToNull(register.getNickname()) == null){
//            userCreate.setNickname(register.getUsername());
//        }else{
//            userCreate.setNickname(register.getNickname());
//        }
//        userCreate.setUsername(register.getUsername());
//        userCreate.setSalt(salt);
//        userCreate.setPassword(encryptPassword);
//        userService.createUser(userCreate);
//    }
//
//
//    /**
//     * 找回密码
//     * @param type      类型：1-手机，2-邮箱
//     * @param receiver  手机号码或邮箱
//     * @param password  新密码
//     * @param code      验证码
//     * @return
//     */
//    @PutMapping(value = "/find_password")
//    @ApiOperation(value = "找回密码")
//    public void findPassword(@RequestParam(value = "type") Integer type,
//                                  @RequestParam(value = "receiver") String receiver,
//                                  @RequestParam(value = "password") String password,
//                                  @RequestParam(value = "code") String code) {
//        UserQuery userQuery = new UserQuery();
//        UserModel userModel = null;
//        switch (type){
//            case FIND_TYPE_EMAIL:
//                Assert.isTrue(EmailValidator.getInstance().isValid(receiver), "请输入正确格式的邮箱");
//                userQuery.setEmail(receiver);
//                userModel = userService.getOneUserModel(userQuery);
//                Assert.notNull(userModel, "该邮箱未注册");
//                break;
//            case FIND_TYPE_PHONE:
//                Assert.isTrue(PhoneValidator.getInstance().isValid(receiver), "请输入正确格式的手机号码");
//                userQuery.setPhone(receiver);
//                userModel = userService.getOneUserModel(userQuery);
//                Assert.notNull(userModel, "该手机号码未注册");
//                break;
//            default:
//                throw new IllegalArgumentException("找回方式仅支持：1-邮箱找回，2-手机找回");
//        }
//        Assert.isTrue(verificationCodeService.checkCode(receiver, code), "请输入有效的验证码");
//        String newSalt = UUIDGenerator.getInstance().getUUID();
//        String newPassword = PasswordHelper.encryptPassword(password, newSalt);
//        User user = new User();
//        user.setSalt(newSalt);
//        user.setPassword(newPassword);
//        user.setId(userModel.getId());
//        userService.updateUser(user);
//    }
//
//
//    /**
//     * 发送验证码
//     * @param type      类型：1-手机，2-邮箱
//     * @param receiver  手机号码或邮箱
//     */
//    @ApiOperation(value = "发送验证码")
//    @PostMapping(value = "/code")
//    @ResponseBody
//    public void setValidateCode(@RequestParam(value = "type") Integer type,
//                                @RequestParam(value = "receiver") String receiver){
//        switch (type){
//            case FIND_TYPE_EMAIL:
//                Assert.isTrue(EmailValidator.getInstance().isValid(receiver), "请输入正确格式的邮箱");
//                verificationCodeService.sendCode(receiver);
//                // TODO 待接入邮件服务
//                break;
//            case FIND_TYPE_PHONE:
//                Assert.isTrue(PhoneValidator.getInstance().isValid(receiver), "请输入正确格式的手机号码");
//                verificationCodeService.sendCode(receiver);
//                // TODO 待接入短信服务
//                break;
//            default:
//                throw new IllegalArgumentException("找回方式仅支持：1-邮箱找回，2-手机找回");
//        }
//    }



}
