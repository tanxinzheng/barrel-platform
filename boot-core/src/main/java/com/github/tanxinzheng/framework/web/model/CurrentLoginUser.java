package com.github.tanxinzheng.framework.web.model;

import lombok.Data;

import java.util.Set;

@Data
public class CurrentLoginUser {

    private String id;
    private String username;
    private String name;
    private String avatar;
    private String email;
    private String phone;
    private Set<String> roles;

}
