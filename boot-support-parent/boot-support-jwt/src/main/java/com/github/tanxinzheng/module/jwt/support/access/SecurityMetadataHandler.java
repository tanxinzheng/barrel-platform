package com.github.tanxinzheng.module.jwt.support.access;

import java.util.List;

/**
 * Created by tanxinzheng on 2018/10/22.
 */
public interface SecurityMetadataHandler {

    /**
     * 加载所有权限
     * @return
     */
    List<PermissionGrantedAuthority> loadAllPermission();
}
