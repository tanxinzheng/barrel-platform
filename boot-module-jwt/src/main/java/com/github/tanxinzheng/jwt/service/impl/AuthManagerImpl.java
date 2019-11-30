package com.github.tanxinzheng.jwt.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.tanxinzheng.jwt.config.JwtConfigProperties;
import com.github.tanxinzheng.jwt.dao.entity.UserDO;
import com.github.tanxinzheng.jwt.dao.mapper.AuthMapper;
import com.github.tanxinzheng.jwt.service.AuthManager;
import com.github.tanxinzheng.jwt.support.JwtUser;
import com.github.tanxinzheng.jwt.support.JwtUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class AuthManagerImpl implements AuthManager {

    @Resource
    AuthMapper authMapper;

    @Resource
    JwtUtils jwtUtils;

    @Resource
    JwtConfigProperties jwtConfigProperties;

    @Override
    public JwtUser findUserByUsername(String username) {
        UserDO userDO = authMapper.selectOne(Wrappers.<UserDO>lambdaQuery().eq(UserDO::getUsername, username));
        if(userDO == null){
            return null;
        }
        String token = jwtUtils.createToken(username, jwtConfigProperties.getIssuer());
        String refreshToken = jwtUtils.createRefreshToken(username, jwtConfigProperties.getIssuer());
        JwtUser jwtUser = new JwtUser();
        jwtUser.setAccountNonExpired(Boolean.TRUE);
        jwtUser.setAccountNonLocked(Boolean.TRUE);
        jwtUser.setCredentialsNonExpired(Boolean.TRUE);
        jwtUser.setId(userDO.getId());
        jwtUser.setEmail(userDO.getEmail());
        jwtUser.setName(userDO.getName());
        jwtUser.setPassword(userDO.getPassword());
        jwtUser.setToken(token);
        jwtUser.setRefreshToken(refreshToken);
        jwtUser.setUsername(userDO.getUsername());
        return jwtUser;
    }

    @Override
    public List loadAllPermission() {
        return null;
    }
}
