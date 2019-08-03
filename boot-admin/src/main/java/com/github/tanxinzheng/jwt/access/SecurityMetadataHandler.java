package com.github.tanxinzheng.jwt.access;

import java.util.List;

/**
 * Created by tanxinzheng on 2018/10/22.
 */
public interface SecurityMetadataHandler {

    /**
     * 加载所有权限
     * @return
     */
    public List<PermissionGrantedAuthority> loadAllPermission();
}
