package com.github.tanxinzheng.module.jwt.support;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * Created by tanxinzheng on 17/8/18.
 */
public class TestJwtLoadService implements JwtLoadService<JwtUser> {

    /**
     * @param username
     * @return
     */
    @Override
    public JwtUser loadUserDetail(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        JwtUser jwtUser = new JwtUser();
        jwtUser.setId("1");
        jwtUser.setName("管理员");
        jwtUser.setPassword("123456");
        jwtUser.setUsername("admin");
        jwtUser.setEmail("admin@xmomen.com");
        return jwtUser;
    }

    /**
     * 加载用户角色，权限
     *
     * @param jwtUser
     * @return
     */
    @Override
    public JwtUser loadAuthorities(JwtUser jwtUser) {
        jwtUser.setRoles(Sets.newHashSet("ROLE_USER"));
        jwtUser.setPermissions(Sets.newHashSet("ROLE_ADMIN:CREATE", "ROLE_USER:CREATE"));
        List<SimpleGrantedAuthority> authorityList = Lists.newArrayList();
        jwtUser.getRoles().stream().forEach(role -> {
            authorityList.add(new SimpleGrantedAuthority(role));
        });
        jwtUser.setAuthorities(authorityList);
        return jwtUser;
    }

    /**
     * 验证密码是否正确
     *
     * @param rawPassword
     * @param userDetails
     * @return
     */
    @Override
    public boolean matchPassword(String rawPassword, JwtUser userDetails) {
        // 若密码需要MD5盐值加密，则自行通过用户名查询盐值，进行判断，请参考#
        if(StringUtils.equals(rawPassword, userDetails.getPassword())){
            return true;
        }
        return false;
    }
}
