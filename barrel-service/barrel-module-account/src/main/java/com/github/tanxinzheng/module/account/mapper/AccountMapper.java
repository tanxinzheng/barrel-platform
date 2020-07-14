package com.github.tanxinzheng.module.account.mapper;

import org.apache.ibatis.annotations.Param;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Date 2020/7/14
 */
public interface AccountMapper {

    int updatePassword(@Param(value = "userId") String userId,
                           @Param(value = "newSalt") String newSalt,
                           @Param(value = "newPassword") String newPassword);
}
