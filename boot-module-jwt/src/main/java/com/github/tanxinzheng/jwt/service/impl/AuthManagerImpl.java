package com.github.tanxinzheng.jwt.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import com.github.tanxinzheng.jwt.dao.entity.UserDO;
import com.github.tanxinzheng.jwt.dao.mapper.AuthMapper;
import com.github.tanxinzheng.framework.core.service.AuthManager;
import com.github.tanxinzheng.jwt.support.JwtUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class AuthManagerImpl implements AuthManager {

    @Resource
    AuthMapper authMapper;

    @Override
    public CurrentLoginUser findUserByUsername(String username) {
        UserDO userDO = authMapper.selectOne(Wrappers.<UserDO>lambdaQuery().eq(UserDO::getUsername, username));
        if(userDO == null){
            return null;
        }
        CurrentLoginUser currentLoginUser = new CurrentLoginUser();
        currentLoginUser.setAccountNonExpired(Boolean.TRUE);
        currentLoginUser.setAccountNonLocked(Boolean.TRUE);
        currentLoginUser.setCredentialsNonExpired(Boolean.TRUE);
        currentLoginUser.setId(userDO.getId());
        currentLoginUser.setSalt(userDO.getSalt());
        currentLoginUser.setEmail(userDO.getEmail());
        currentLoginUser.setName(userDO.getName());
        currentLoginUser.setPassword(userDO.getPassword());
        currentLoginUser.setUsername(userDO.getUsername());
        return currentLoginUser;
    }

    @Override
    public List loadAllPermission() {
        return null;
    }

    @Override
    public String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            return null;
        }
        JwtUser jwtUser = (JwtUser) authentication.getDetails();
        return jwtUser.getId();
    }
}
