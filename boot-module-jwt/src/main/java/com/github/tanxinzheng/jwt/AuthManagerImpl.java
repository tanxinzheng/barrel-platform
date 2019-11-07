package com.github.tanxinzheng.jwt;

import com.github.tanxinzheng.jwt.support.JwtUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthManagerImpl implements AuthManager {
    @Override
    public JwtUser findUserByUsername(String username) {
        return null;
    }

    @Override
    public List loadAllPermission() {
        return null;
    }
}
