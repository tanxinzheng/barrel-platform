package com.github.tanxinzheng.framework.web.authentication;

import java.util.Set;

/**
 * 当前用户信息接口
 * @param <T>
 */
public interface CurrentAccountService<T> {

    /**
     * 当前用户使用登录
     * @return
     */
    boolean isAuthenticated();

    /**
     * 当前用户ID
     * @return
     */
    String getAccountId();

    /**
     * 当前用户名称
     * @return
     */
    String getAccountName();

    /**
     * 当前用户角色
     * @return
     */
    Set<String> getRoles();

    /**
     * 当前用户权限资源
     * @return
     */
    Set<String> getPermissions();

}
