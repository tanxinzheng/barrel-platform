package com.github.tanxinzheng.module.system.authorization.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.module.system.authorization.domain.dto.RoleDTO;
import com.github.tanxinzheng.module.system.authorization.domain.entity.RoleDO;

import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
public interface RoleService {

    /**
     * 新增角色
     * @param  roleCreate
     * @return RoleDTO
     */
    public RoleDTO createRole(RoleDTO roleCreate);

    /**
     * 批量新增角色
     * @param role
     * @return List<Role>
     */
    List<RoleDTO> createRoles(List<RoleDTO> role);

    /**
     * 更新角色
     * @param   roleUpdate
     * @return  boolean
     */
    public boolean updateRole(RoleDTO roleUpdate);

    /**
     * 主键查询对象
     * @param   id
     * @return  RoleDTO
     */
    public RoleDTO findById(String id);


    /**
    * 查询集合对象
    * @param queryWrapper
    * @return List<RoleDTO>
    */
    public List<RoleDTO> findList(QueryWrapper<RoleDO> queryWrapper);

    /**
     * 查询角色领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    public IPage<RoleDTO> findPage(IPage<RoleDO> page, QueryWrapper<RoleDO> queryWrapper);

    /**
     * 批量删除角色
     * @param  ids
     * @return boolean
     */
    public boolean deleteRole(List<String> ids);

    /**
     * 删除角色
     * @param  id
     * @return boolean
     */
    public boolean deleteRole(String id);

}
