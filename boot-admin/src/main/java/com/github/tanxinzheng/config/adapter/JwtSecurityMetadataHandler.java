package com.github.tanxinzheng.config.adapter;

import com.github.tanxinzheng.module.authorization.service.GroupPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

//import com.github.tanxinzheng.module.jwt.support.access.PermissionGrantedAuthority;
//import com.github.tanxinzheng.module.jwt.support.access.SecurityMetadataHandler;

/**
 * Created by tanxinzheng on 2018/10/22.
 */
public class JwtSecurityMetadataHandler {

    @Autowired
    GroupPermissionService groupPermissionService;

    /**
     * 加载所有权限
     *
     * @return
     */
//    @Cacheable(value = "ALL_GROUP_PERMISSIONS")
//    @Override
//    public List<PermissionGrantedAuthority> loadAllPermission() {
//        List<PermissionModel> list = groupPermissionService.getGroupPermissions(new GroupPermissionQuery());
//        Map<String, PermissionGrantedAuthority> map = Maps.newHashMap();
//        for (PermissionModel permissionModel : list) {
//            String key = permissionModel.getPermissionAction() + ":" + permissionModel.getPermissionUrl();
//            PermissionGrantedAuthority authority = new PermissionGrantedAuthority();
//            authority.setUrl(permissionModel.getPermissionUrl());
//            authority.setRequestMethod(PermissionAction.valueOf(permissionModel.getPermissionAction()).getRequestMethod());
//            if(map.get(key) == null){
//                authority.setRoles(Lists.newArrayList(permissionModel.getGroupCode()));
//                map.put(key, authority);
//            }else{
//                map.get(key).getRoles().add(permissionModel.getGroupCode());
//            }
//        }
//        return Lists.newArrayList(map.values());
//    }
}
