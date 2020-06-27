package com.github.tanxinzheng.module.authorization.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.module.authorization.domain.dto.PermissionDTO;
import com.github.tanxinzheng.module.authorization.domain.dto.RolePermissionRelationDTO;
import com.github.tanxinzheng.module.authorization.domain.entity.RolePermissionRelationDO;

import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
public interface RolePermissionRelationService {

    /**
     * 新增角色权限关系
     * @param  rolePermissionRelationCreate
     * @return RolePermissionRelationDTO
     */
    public RolePermissionRelationDTO createRolePermissionRelation(RolePermissionRelationDTO rolePermissionRelationCreate);

    /**
     * 批量新增角色权限关系
     * @param rolePermissionRelation
     * @return List<RolePermissionRelation>
     */
    List<RolePermissionRelationDTO> createRolePermissionRelations(List<RolePermissionRelationDTO> rolePermissionRelation);

    /**
     * 更新角色权限关系
     * @param   rolePermissionRelationUpdate
     * @return  boolean
     */
    public boolean updateRolePermissionRelation(RolePermissionRelationDTO rolePermissionRelationUpdate);

    /**
     * 主键查询对象
     * @param   id
     * @return  RolePermissionRelationDTO
     */
    public RolePermissionRelationDTO findById(String id);


    /**
    * 查询集合对象
    * @param queryWrapper
    * @return List<RolePermissionRelationDTO>
    */
    public List<RolePermissionRelationDTO> findList(QueryWrapper<RolePermissionRelationDO> queryWrapper);

    /**
     * 查询角色权限关系领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    public IPage<RolePermissionRelationDTO> findPage(IPage<RolePermissionRelationDO> page, QueryWrapper<RolePermissionRelationDO> queryWrapper);

    /**
     * 批量删除角色权限关系
     * @param  ids
     * @return boolean
     */
    public boolean deleteRolePermissionRelation(List<String> ids);

    /**
     * 删除角色权限关系
     * @param  id
     * @return boolean
     */
    public boolean deleteRolePermissionRelation(String id);

    /**
     * 关联角色权限
     * @param roleId
     * @param permissionIds
     * @param isRelate    是否关联： 绑定-true，解绑-false
     * @return
     */
    public boolean relatePermission(String roleId, List<String> permissionIds, boolean isRelate);

    /**
     * 查询角色已、未关联权限
     * @param roleId    角色主键
     * @param isRelate  true-已关联，false-未关联
     * @return
     */
    public IPage<PermissionDTO> findRolePermission(long pageNum, long pageSize, String roleId, boolean isRelate);
}
