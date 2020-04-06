package com.github.tanxinzheng.framework.core.service;

import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;

import java.util.List;

public interface AuthManager {

    public CurrentLoginUser findUserByUsername(String username);

    public List loadAllPermission();

    public String getCurrentUserId();
}
