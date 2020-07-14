package com.github.tanxinzheng.module.system.authorization.feign;

import com.github.tanxinzheng.framework.model.BaseResultCode;
import com.github.tanxinzheng.framework.model.Result;
import com.github.tanxinzheng.framework.secure.domain.AuthUser;
import com.github.tanxinzheng.module.auth.feign.IUserClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserClientFallback implements IUserClient {


    @Override
    public Result<AuthUser> getUserByUsername(String username) {
        return Result.failed(BaseResultCode.SYSTEM_ERROR);
    }

    /**
     * 查询主键用户
     *
     * @param userId
     * @return
     */
    @Override
    public Result<AuthUser> getUserByUserId(String userId) {
        return Result.failed(BaseResultCode.SYSTEM_ERROR);
    }

    @Override
    public Result<List<String>> getRoles(String username) {
        return Result.failed(BaseResultCode.SYSTEM_ERROR);
    }
}
