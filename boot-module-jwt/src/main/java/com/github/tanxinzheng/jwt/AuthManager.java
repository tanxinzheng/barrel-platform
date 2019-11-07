package com.github.tanxinzheng.jwt;

import com.github.tanxinzheng.jwt.support.JwtUser;

import java.util.List;

public interface AuthManager {

    public JwtUser findUserByUsername(String username);

    public List loadAllPermission();
}
