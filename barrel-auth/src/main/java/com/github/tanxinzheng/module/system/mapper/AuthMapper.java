package com.github.tanxinzheng.module.system.mapper;

import com.github.tanxinzheng.framework.secure.domain.AuthUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
@Mapper
public interface AuthMapper {

    AuthUser getAuthUserByUsername(@Param(value = "username") String username);

    List<String> getUserRoles(@Param(value = "userId") String userId);
}