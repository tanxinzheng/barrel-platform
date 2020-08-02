package com.github.tanxinzheng.module.system.authorization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.tanxinzheng.module.system.authorization.domain.dto.RoleDTO;
import com.github.tanxinzheng.module.system.authorization.domain.entity.RoleDO;
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
public interface RoleMapper extends BaseMapper<RoleDO> {

    List<RoleDTO> findUserRoles(@Param("userId") String userId,
                                @Param("isRelate") Boolean isRelate);
}