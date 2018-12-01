package com.github.tanxinzheng.module.account.service.impl;

import org.springframework.stereotype.Service;

/**
 * Created by tanxinzheng on 16/9/2.
 */
@Service
public class AccountServiceImpl2 {

//    @Autowired
//    UserService userService;
//
//    @Autowired
//    VerificationCodeService verificationCodeService;
//
//    @Autowired
//    UserGroupService userGroupService;
//
//    /**
//     * 查询帐号
//     * @param username
//     * @return
//     */
//    public AccountModel getAccountModelByUsername(String username) {
//        UserModel user = userService.getOneUserModelByUsername(username);
//        if(user == null){
//            return null;
//        }
//        return setAccountModel(user);
//    }
//
//    /**
//     * 查询帐号
//     * @param userId
//     * @return
//     */
//    public AccountModel getAccountModelByUserId(String userId) {
//        UserModel user = userService.getOneUserModel(userId);
//        if(user == null){
//            return null;
//        }
//        return setAccountModel(user);
//    }
//
//    private AccountModel setAccountModel(UserModel user){
//        AccountModel accountModel = new AccountModel();
//        accountModel.setLocked(false);
//        accountModel.setUsername(user.getUsername());
//        accountModel.setEmail(user.getEmail());
//        accountModel.setNickname(user.getNickname());
//        accountModel.setPhoneNumber(user.getPhoneNumber());
//        accountModel.setCreatedTime(user.getCreatedTime());
//        accountModel.setLastLoginTime(user.getLastLoginTime());
//        accountModel.setUserId(user.getId());
//        accountModel.setAvatar(user.getAvatar());
//        return accountModel;
//    }
//
//    /**
//     * 用户注册
//     * @param register
//     * @return
//     */
//    @Transactional
//    public void register(Register register) {
//        UserModel checkUser = userService.getOneUserModelByUsername(register.getUsername());
//        Assert.isNull(checkUser, "该用户名已被注册");
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
//        UserModel userModel = userService.createUser(userCreate);
//        // 新增用户，默认添加XMO_USER用户组
//        userGroupService.createUserGroupByCode(userModel.getId(), UserGroupEnum.XMO_USER.name());
//    }
//
//    /**
//     * 获取SessionModel
//     * @return
//     */
//    public AccountModel getCurrentAccount(){
//        AccountModel accountModel = getAccountModelByUsername(getUsername());
//        return accountModel;
//    }
//
//    private String getUsername(){
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = null;
//        if (principal instanceof UserDetails) {
//             username = ((UserDetails)principal).getUsername();
//        } else {
//            username = principal.toString();
//        }
//        return username;
//    }
//
//    /**
//     * 查询角色
//     * @param userId
//     * @return
//     */
//    public Set<String> findRoles(String userId){
//        UserGroupQuery userGroupQuery = new UserGroupQuery();
//        userGroupQuery.setUserId(userId);
//        userGroupQuery.setHasBindGroup(true);
//        List<GroupModel> groupList = userGroupService.getUserGroups(userGroupQuery);
//        Set<String> roles = new HashSet<>();
//        for (GroupModel groupModel : groupList) {
//            roles.add(groupModel.getGroupCode());
//        }
//        return roles;
//    }
//
//    /**
//     * 查询权限
//     * @param userId
//     * @return
//     */
//    public Set<String> findPermissions(String userId){
//        UserPermissionQuery userPermissionQuery = new UserPermissionQuery();
//        userPermissionQuery.setUserId(userId);
////        List<PermissionModel> permissionModelList = userPermissionService.getUserPermissions(userPermissionQuery);
//        Set<String> permissions = new HashSet<>();
////        for (PermissionModel permissionModel : permissionModelList) {
////            permissions.add(permissionModel.getPermissionCode());
////        }
//        return permissions;
//    }
}
