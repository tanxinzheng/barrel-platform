package com.github.tanxinzheng.module.system.authorization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.module.system.authorization.domain.dto.PermissionDTO;
import com.github.tanxinzheng.module.system.authorization.domain.entity.PermissionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
@Mapper
public interface PermissionMapper extends BaseMapper<PermissionDO> {

    IPage<PermissionDTO> findRolePermission(IPage<PermissionDTO> page,
                                            @Param("roleId") String roleId,
                                            @Param("isRelate") Boolean isRelate);
}