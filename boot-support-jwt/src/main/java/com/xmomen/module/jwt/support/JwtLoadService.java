package com.xmomen.module.jwt.support;

import java.util.Set;

/**
 * Created by tanxinzheng on 2018/9/23.
 */
public interface JwtLoadService {

    /**
     *
     * @param username
     * @return
     */
    JwtUserDetails loadUserDetail(String username);


    /**
     * 验证密码是否正确
     * @param rawPassword
     * @param userDetails
     * @return
     */
    boolean matchPassword(String rawPassword, JwtUserDetails userDetails);

}
