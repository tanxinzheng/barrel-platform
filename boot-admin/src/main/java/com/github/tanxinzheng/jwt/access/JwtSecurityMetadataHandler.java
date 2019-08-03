package com.github.tanxinzheng.jwt.access;

import com.github.tanxinzheng.module.authorization.model.GroupPermissionQuery;
import com.github.tanxinzheng.module.authorization.model.PermissionModel;
import com.github.tanxinzheng.module.authorization.service.GroupPermissionService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by tanxinzheng on 2018/10/22.
 */
public class JwtSecurityMetadataHandler implements SecurityMetadataHandler {

    @Autowired
    GroupPermissionService groupPermissionService;

    /**
     * 加载所有权限
     *
     * @return
     */
    @Cacheable(value = "ALL_GROUP_PERMISSIONS")
    @Override
    public List<PermissionGrantedAuthority> loadAllPermission() {
        List<PermissionModel> list = groupPermissionService.getGroupPermissions(new GroupPermissionQuery());
        Map<String, PermissionGrantedAuthority> map = Maps.newHashMap();
        for (PermissionModel permissionModel : list) {
            String key = permissionModel.getPermissionKey();
            PermissionGrantedAuthority authority = new PermissionGrantedAuthority();
            authority.setUrl(permissionModel.getPermissionUrl());
            authority.setRequestMethod(RequestMethod.valueOf(permissionModel.getPermissionAction()));
            if(map.get(key) == null){
                authority.setRoles(Lists.newArrayList(permissionModel.getGroupCode()));
                map.put(key, authority);
            }else{
                map.get(key).getRoles().add(permissionModel.getGroupCode());
            }
        }
        return Lists.newArrayList(map.values());
    }
}
