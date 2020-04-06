package com.github.tanxinzheng.jwt.support;

import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import com.github.tanxinzheng.framework.core.service.AuthManager;
import com.google.common.collect.Lists;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Resource
    JwtUtils jwtUtils;

    @Resource
    AuthManager authManager;

    /**
     * Performs authentication with the same contract as
     * {@link AuthenticationManager#authenticate(Authentication)}
     * .
     *
     * @param authentication the authentication request object.
     * @return a fully authenticated object including credentials. May return
     * <code>null</code> if the <code>AuthenticationProvider</code> is unable to support
     * authentication of the passed <code>Authentication</code> object. In such a case,
     * the next <code>AuthenticationProvider</code> that supports the presented
     * <code>Authentication</code> class will be tried.
     * @throws AuthenticationException if authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = ((JwtAuthenticationToken)authentication).getToken();
        String username = jwtUtils.getUsernameByToken(token);
        CurrentLoginUser currentLoginUser = authManager.findUserByUsername(username);
        if(currentLoginUser == null){
            throw new UsernameNotFoundException("该用户名未注册");
        }
        List<SimpleGrantedAuthority> authorityList = Lists.newArrayList();
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        JwtUser jwtUser = new JwtUser(currentLoginUser.getId(), currentLoginUser.getName(),
                currentLoginUser.getUsername(), currentLoginUser.getPermissions());
        jwtUser.setAccountNonExpired(currentLoginUser.isAccountNonExpired());
        jwtUser.setAccountNonLocked(currentLoginUser.isAccountNonLocked());
        jwtUser.setCredentialsNonExpired(currentLoginUser.isCredentialsNonExpired());
        jwtUser.setEmail(currentLoginUser.getEmail());
        jwtUser.setEnabled(currentLoginUser.isEnabled());
        jwtUser.setPermissions(currentLoginUser.getPermissions());
        jwtUser.setRoles(currentLoginUser.getRoles());
        jwtUser.setSalt(currentLoginUser.getSalt());
        jwtUser.setPassword(currentLoginUser.getPassword());
        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(token, jwtUser, authorityList);
        authenticationToken.setAuthenticated(Boolean.TRUE);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return authenticationToken;
    }

    /**
     * Returns <code>true</code> if this <Code>AuthenticationProvider</code> supports the
     * indicated <Code>Authentication</code> object.
     * <p>
     * Returning <code>true</code> does not guarantee an
     * <code>AuthenticationProvider</code> will be able to authenticate the presented
     * instance of the <code>Authentication</code> class. It simply indicates it can
     * support closer evaluation of it. An <code>AuthenticationProvider</code> can still
     * return <code>null</code> from the {@link #authenticate(Authentication)} method to
     * indicate another <code>AuthenticationProvider</code> should be tried.
     * </p>
     * <p>
     * Selection of an <code>AuthenticationProvider</code> capable of performing
     * authentication is conducted at runtime the <code>ProviderManager</code>.
     * </p>
     *
     * @param authentication
     * @return <code>true</code> if the implementation can more closely evaluate the
     * <code>Authentication</code> class presented
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
