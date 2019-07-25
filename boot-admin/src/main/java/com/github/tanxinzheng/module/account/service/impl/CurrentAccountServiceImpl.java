package com.github.tanxinzheng.module.account.service.impl;

import com.github.tanxinzheng.framework.web.authentication.CurrentAccountService;
import com.github.tanxinzheng.jwt.support.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;

/**
 * Created by tanxinzheng on 2018/9/28.
 */
public class CurrentAccountServiceImpl implements CurrentAccountService<JwtUser> {
    /**
     * 当前用户使用登录
     *
     * @return
     */
    @Override
    public boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    /**
     * 当前用户ID
     *
     * @return
     */
    @Override
    public String getAccountId() {
        JwtUser jwtUser = getAccountDetail();
        if(jwtUser == null){
            return null;
        }
        return jwtUser.getId();
    }

    /**
     * 当前用户名称
     *
     * @return
     */
    @Override
    public String getAccountName() {
        JwtUser jwtUser = getAccountDetail();
        if(jwtUser == null){
            return null;
        }
        return jwtUser.getName();
    }

    /**
     * 当前用户明细
     *
     * @return
     */
    private JwtUser getAccountDetail() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if(object instanceof JwtUser){
            return (JwtUser) object;
        }
        return null;
    }

    /**
     * 当前用户角色
     *
     * @return
     */
    @Override
    public Set<String> getRoles() {
        JwtUser jwtUser = getAccountDetail();
        if(jwtUser == null){
            return null;
        }
        return jwtUser.getRoles();
    }

    /**
     * 当前用户权限资源
     *
     * @return
     */
    @Override
    public Set<String> getPermissions() {
        JwtUser jwtUser = getAccountDetail();
        if(jwtUser == null){
            return null;
        }
        return jwtUser.getPermissions();
    }
}
