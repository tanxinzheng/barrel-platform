package com.xmomen.framework.web.authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;

/**
 * Created by tanxinzheng on 2018/9/20.
 */
public class CurrentAccountServiceBySSS implements CurrentAccountService {

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
        return String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    /**
     * 当前用户名称
     *
     * @return
     */
    @Override
    public String getAccountName() {
        return String.valueOf(SecurityContextHolder.getContext().getAuthentication().getDetails());
    }

    /**
     * 当前用户明细
     *
     * @return
     */
    @Override
    public Object getAccountDetail() {
        return null;
    }

    /**
     * 当前用户角色
     *
     * @return
     */
    @Override
    public Set<String> getRoles() {
        return null;
    }

    /**
     * 当前用户权限资源
     *
     * @return
     */
    @Override
    public Set<String> getPermissions() {
        return null;
    }
}
