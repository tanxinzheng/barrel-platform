package com.github.tanxinzheng.module.system.authorization.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.module.system.authorization.domain.dto.RoleDTO;
import com.github.tanxinzheng.module.system.authorization.domain.dto.UserRoleRelationDTO;
import com.github.tanxinzheng.module.system.authorization.domain.entity.UserRoleRelationDO;

import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
public interface UserRoleRelationService {

    /**
     * 新增用户角色关系
     * @param  userRoleRelationCreate
     * @return UserRoleRelationDTO
     */
    public UserRoleRelationDTO createUserRoleRelation(UserRoleRelationDTO userRoleRelationCreate);

    /**
     * 批量新增用户角色关系
     * @param userRoleRelation
     * @return List<UserRoleRelation>
     */
    List<UserRoleRelationDTO> createUserRoleRelations(List<UserRoleRelationDTO> userRoleRelation);

    /**
     * 更新用户角色关系
     * @param   userRoleRelationUpdate
     * @return  boolean
     */
    public boolean updateUserRoleRelation(UserRoleRelationDTO userRoleRelationUpdate);

    /**
     * 主键查询对象
     * @param   id
     * @return  UserRoleRelationDTO
     */
    public UserRoleRelationDTO findById(String id);


    /**
    * 查询集合对象
    * @param queryWrapper
    * @return List<UserRoleRelationDTO>
    */
    public List<UserRoleRelationDTO> findList(QueryWrapper<UserRoleRelationDO> queryWrapper);

    /**
     * 查询用户角色关系领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    public IPage<UserRoleRelationDTO> findPage(IPage<UserRoleRelationDO> page, QueryWrapper<UserRoleRelationDO> queryWrapper);

    /**
     * 批量删除用户角色关系
     * @param  ids
     * @return boolean
     */
    public boolean deleteUserRoleRelation(List<String> ids);

    /**
     * 删除用户角色关系
     * @param  id
     * @return boolean
     */
    public boolean deleteUserRoleRelation(String id);

    /**
     * 用户绑定、解绑角色权限
     * @param userId
     * @param roleIds
     * @param isRelate    是否关联： 绑定-true，解绑-false
     * @return
     */
    public boolean relateRoles(String userId, List<String> roleIds, boolean isRelate);

    /**
     * 查询用户已、未关联角色权限
     * @param userId    用户主键
     * @param isRelate  true-已关联，false-未关联
     * @return
     */
    public List<RoleDTO> findUserRole(String userId, boolean isRelate);

}
