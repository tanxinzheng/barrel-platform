package com.github.tanxinzheng.config;

import com.github.tanxinzheng.module.authorization.service.GroupPermissionService;
import com.github.tanxinzheng.module.authorization.service.UserGroupService;
import com.github.tanxinzheng.module.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tanxinzheng on 17/8/18.
 */
//@Configuration
public class WebSecurityConfig {

    @Autowired
    UserService userService;

    @Autowired
    UserGroupService userGroupService;;

    @Autowired
    GroupPermissionService groupPermissionService;

//    @Bean
//    @Override
//    public JwtLoadService getJwtLoadService() {
//        return new JwtLoadService<JwtUser>() {
//            @Override
//            public JwtUser loadUserDetail(String username) {
//                UserModel userModel = userService.getOneUserModelByUsername(username);
//                if(userModel == null){
//                    return null;
//                }
//                JwtUser jwtUser = new JwtUser();
//                jwtUser.setId(userModel.getId());
//                jwtUser.setUsername(userModel.getUsername());
//                jwtUser.setPassword(userModel.getPassword());
//                jwtUser.setName(userModel.getNickname());
//                jwtUser.setEmail(userModel.getEmail());
//                return jwtUser;
//            }
//
//            /**
//             * 加载用户角色，权限
//             *
//             * @param jwtUser
//             * @return
//             */
//            @Override
//            public JwtUser loadAuthorities(JwtUser jwtUser) {
//                UserGroupQuery userGroupQuery = new UserGroupQuery();
//                userGroupQuery.setUserId(jwtUser.getId());
//                List<GroupModel> list = userGroupService.getUserGroups(userGroupQuery);
//                Set<String> roles = Sets.newHashSet();
//                roles.add("ROLE_USER");
//                if(CollectionUtils.isNotEmpty(list)){
//                    list.stream().forEach(groupModel -> {
//                        roles.add(groupModel.getGroupCode());
//                    });
//                }
//                jwtUser.setRoles(roles);
//                Set<String> permissions = Sets.newHashSet();
//                GroupPermissionQuery groupPermissionQuery = new GroupPermissionQuery();
//                groupPermissionQuery.setGroupCodes(roles.toArray(new String[roles.size()]));
//                List<PermissionModel> permissionList = groupPermissionService.getGroupPermissions(groupPermissionQuery);
//                if(CollectionUtils.isNotEmpty(permissionList)){
//                    permissionList.stream().forEach(permissionModel -> {
//                        permissions.add(permissionModel.getPermissionKey());
//                    });
//                }
//                jwtUser.setPermissions(permissions);
//                List<SimpleGrantedAuthority> authorityList = Lists.newArrayList();
//                jwtUser.getRoles().stream().forEach(role -> {
//                    authorityList.add(new SimpleGrantedAuthority(role));
//                });
//                jwtUser.setAuthorities(authorityList);
//                return jwtUser;
//            }
//
//            @Override
//            public boolean matchPassword(String rawPassword, JwtUser userDetails) {
//                UserModel userModel = userService.getOneUserModelByUsername(userDetails.getUsername());
//                if(PasswordHelper.encryptPassword(rawPassword, userModel.getSalt()).equals(userDetails.getPassword())){
//                    return true;
//                }
//                return false;
//            }
//        };
//    }
//
//    @Bean
//    @Override
//    public SecurityMetadataHandler getSecurityMetadataHandler() {
//        return new JwtSecurityMetadataHandler();
//    }
}
