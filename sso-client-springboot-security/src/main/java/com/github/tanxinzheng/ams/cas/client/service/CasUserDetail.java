package com.github.tanxinzheng.ams.cas.client.service;

import com.google.common.collect.Sets;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by tanxinzheng on 2018/8/30.
 */
@Data
public class CasUserDetail implements UserDetails {

    private String username;
    private String password;
    private Set<String> roles;
    private Set<String> permissions;
    private Map<String, Object> attributes;
    private Set<SimpleGrantedAuthority> authorities;

    public CasUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities){
        this.username = username;
        this.password = password;
        this.authorities = Sets.newHashSet(new SimpleGrantedAuthority("ROLE_GUEST"));
        if(CollectionUtils.isNotEmpty(authorities)){
            authorities.stream().forEach(grantedAuthority -> {
                this.authorities.add(new SimpleGrantedAuthority(grantedAuthority.getAuthority()));
            });
        }
    }

    public CasUserDetail(String username, String password, Set<String> roles, Set<String> permissions) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        if(CollectionUtils.isNotEmpty(roles)){
            this.authorities = Sets.newHashSet();
            roles.stream().forEach(role -> {
                this.authorities.add(new SimpleGrantedAuthority(role));
            });
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
