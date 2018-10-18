package com.xmomen.module.jwt.support;

/**
 * Created by tanxinzheng on 2018/9/23.
 */
public interface JwtLoadService<T extends JwtUserDetails> {

    /**
     * 查询用户信息
     * @param username
     * @return
     */
    T loadUserDetail(String username);


    /**
     * 加载用户角色，权限
     * @param jwtUserDetails
     * @return
     */
    T loadAuthorities(T jwtUserDetails);

    /**
     * 验证密码是否正确
     * @param rawPassword
     * @param userDetails
     * @return
     */
    boolean matchPassword(String rawPassword, T userDetails);

}
