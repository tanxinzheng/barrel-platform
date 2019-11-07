package com.github.tanxinzheng.jwt.handler;

import com.github.tanxinzheng.jwt.AuthManager;
import com.github.tanxinzheng.jwt.access.PermissionGrantedAuthority;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by tanxinzheng on 2018/10/22.
 */
public class TokenSecurityMetadataHandler implements SecurityMetadataHandler {

    @Resource
    AuthManager authManager;

    /**
     * 加载所有权限
     *
     * @return
     */
    @Cacheable(value = "ALL_GROUP_PERMISSIONS")
    @Override
    public List<PermissionGrantedAuthority> loadAllPermission() {
        List<PermissionGrantedAuthority> list = authManager.loadAllPermission();
//        Map<String, PermissionGrantedAuthority> map = Maps.newHashMap();
//        for (PermissionModel permissionModel : list) {
//            String key = permissionModel.getPermissionKey();
//            PermissionGrantedAuthority authority = new PermissionGrantedAuthority();
//            authority.setUrl(permissionModel.getPermissionUrl());
//            authority.setRequestMethod(RequestMethod.valueOf(permissionModel.getPermissionAction()));
//            if(map.get(key) == null){
//                authority.setRoles(Lists.newArrayList(permissionModel.getGroupCode()));
//                map.put(key, authority);
//            }else{
//                map.get(key).getRoles().add(permissionModel.getGroupCode());
//            }
//        }
//        return Lists.newArrayList(map.values());
        return Lists.newArrayList();
    }
}
