package com.github.tanxinzheng.framework.web.model;

import lombok.Data;

import java.util.Set;

@Data
public class CurrentLoginUser {

    private String id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String salt;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Set<String> roles;
    private Set<String> permissions;

}
