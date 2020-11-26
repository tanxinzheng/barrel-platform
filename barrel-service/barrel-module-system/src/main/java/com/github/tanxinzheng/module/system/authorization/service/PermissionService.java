package com.github.tanxinzheng.module.system.authorization.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.module.system.authorization.domain.dto.PermissionDTO;
import com.github.tanxinzheng.module.system.authorization.domain.entity.PermissionDO;

import java.util.List;
import java.util.Map;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
public interface PermissionService {

    /**
     * 新增权限资源
     * @param  permissionCreate
     * @return PermissionDTO
     */
    PermissionDTO createPermission(PermissionDTO permissionCreate);

    /**
     * 批量新增权限资源
     * @param permission
     * @return List<Permission>
     */
    List<PermissionDTO> createPermissions(List<PermissionDTO> permission);

    /**
     * 更新权限资源
     * @param   permissionUpdate
     * @return  boolean
     */
    boolean updatePermission(PermissionDTO permissionUpdate);

    /**
     * 主键查询对象
     * @param   id
     * @return  PermissionDTO
     */
    PermissionDTO findById(String id);


    /**
    * 查询集合对象
    * @param queryWrapper
    * @return List<PermissionDTO>
    */
    List<PermissionDTO> findList(QueryWrapper<PermissionDO> queryWrapper);

    /**
     * 查询权限资源领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<PermissionDTO> findPage(IPage<PermissionDO> page, QueryWrapper<PermissionDO> queryWrapper);

    /**
     * 批量删除权限资源
     * @param  ids
     * @return boolean
     */
    boolean deletePermission(List<String> ids);

    /**
     * 删除权限资源
     * @param  id
     * @return boolean
     */
    boolean deletePermission(String id);

    /**
     * 自动初始化权限表
     * @param swaggerGroup
     * @param updatedBy
     * @return
     */
    Map<String, Integer> autoInitPermissions(String swaggerGroup, String updatedBy);

}
