package com.github.tanxinzheng.module.feign;

import com.github.tanxinzheng.framework.secure.domain.AuthUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserClientFallback implements UserClient {


    @Override
    public AuthUser getUserByUsername(String username) {
        return null;
    }

    @Override
    public List<String> getRoles(String username) {
        return null;
    }
}
