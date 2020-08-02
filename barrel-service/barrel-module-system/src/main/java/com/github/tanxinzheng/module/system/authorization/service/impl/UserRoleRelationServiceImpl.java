package com.github.tanxinzheng.module.system.authorization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.system.authorization.domain.dto.RoleDTO;
import com.github.tanxinzheng.module.system.authorization.domain.dto.UserRoleRelationDTO;
import com.github.tanxinzheng.module.system.authorization.domain.entity.UserRoleRelationDO;
import com.github.tanxinzheng.module.system.authorization.mapper.RoleMapper;
import com.github.tanxinzheng.module.system.authorization.mapper.UserRoleRelationMapper;
import com.github.tanxinzheng.module.system.authorization.service.UserRoleRelationService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
@Slf4j
@Service
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelationDO> implements UserRoleRelationService {

    @Resource
    UserRoleRelationMapper userRoleRelationMapper;

    @Resource
    RoleMapper roleMapper;

    /**
     * 新增用户角色关系
     *
     * @param userRoleRelationDTO
     * @return UserRoleRelationResponse
     */
    @Transactional
    @Override
    public UserRoleRelationDTO createUserRoleRelation(UserRoleRelationDTO userRoleRelationDTO) {
        AssertValid.notNull(userRoleRelationDTO, "userRoleRelationDTO参数不能为空");
        UserRoleRelationDO userRoleRelation = userRoleRelationDTO.toDO(UserRoleRelationDO.class);
        boolean isOk = save(userRoleRelation);
        if(!isOk){
            return null;
        }
        return BeanCopierUtils.copy(userRoleRelation, UserRoleRelationDTO.class);
    }


    /**
     * 更新用户角色关系
     *
     * @param userRoleRelationDTO
     * @return UserRoleRelationResponse
     */
    @Transactional
    @Override
    public boolean updateUserRoleRelation(UserRoleRelationDTO userRoleRelationDTO) {
        AssertValid.notNull(userRoleRelationDTO, "userRoleRelationDTO不能为空");
        UserRoleRelationDO userRoleRelationDO = BeanCopierUtils.copy(userRoleRelationDTO, UserRoleRelationDO.class);
        return updateById(userRoleRelationDO);
    }

    /**
     * 批量新增用户角色关系
     * @param userRoleRelations
     * @return
     */
    @Transactional
    @Override
    public List<UserRoleRelationDTO> createUserRoleRelations(List<UserRoleRelationDTO> userRoleRelations) {
        AssertValid.notEmpty(userRoleRelations, "userRoleRelations参数不能为空");
        List<UserRoleRelationDO> userRoleRelationDOList = BeanCopierUtils.copy(userRoleRelations, UserRoleRelationDO.class);
        boolean isOK = saveBatch(userRoleRelationDOList);
        if(!isOK){
            return Lists.newArrayList();
        }
        List<String> ids = userRoleRelationDOList.stream().map(UserRoleRelationDO::getId).collect(Collectors.toList());
        List<UserRoleRelationDO> data = userRoleRelationMapper.selectBatchIds(ids);
        return BeanCopierUtils.copy(data, UserRoleRelationDTO.class);
    }


    /**
     * 主键查询对象
     *
     * @param id
     * @return UserRoleRelationResponse
     */
    @Override
    public UserRoleRelationDTO findById(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        UserRoleRelationDO userRoleRelation = getById(id);
        return BeanCopierUtils.copy(userRoleRelation, UserRoleRelationDTO.class);
    }

    /**
    * 查询集合对象
    *
    * @param queryWrapper
    * @return List<UserRoleRelationDTO>
    */
    @Override
    public List<UserRoleRelationDTO> findList(QueryWrapper<UserRoleRelationDO> queryWrapper) {
        return BeanCopierUtils.copy(list(queryWrapper), UserRoleRelationDTO.class);
    }
    /**
     * 查询用户角色关系领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<UserRoleRelationDTO> findPage(IPage<UserRoleRelationDO> page, QueryWrapper<UserRoleRelationDO> queryWrapper) {
        IPage<UserRoleRelationDO> data = (Page<UserRoleRelationDO>) page(page, queryWrapper);
        return BeanCopierUtils.copy(data, UserRoleRelationDTO.class);
    }

    /**
     * 批量删除用户角色关系
     *
     * @param ids
     * @return int
     */
    @Transactional
    @Override
    public boolean deleteUserRoleRelation(List<String> ids) {
        AssertValid.notEmpty(ids, "ids参数不能为空");
        return removeByIds(ids);
    }

    /**
     * 删除用户角色关系
     * @param  id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteUserRoleRelation(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        return removeById(id);
    }

    /**
     * 用户绑定、解绑角色权限
     *
     * @param userId
     * @param roleIds
     * @param isRelate 是否关联： 绑定-true，解绑-false
     * @return
     */
    @Override
    public boolean relateRoles(String userId, List<String> roleIds, boolean isRelate) {
        if(CollectionUtils.isEmpty(roleIds)){
            return true;
        }
        LambdaQueryWrapper<UserRoleRelationDO> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(UserRoleRelationDO::getUserId, userId);
        lambdaQuery.in(UserRoleRelationDO::getRoleId, roleIds);
        if(isRelate){
            List<UserRoleRelationDTO> list = Lists.newArrayList();
            for (String roleId : roleIds) {
                UserRoleRelationDTO userRoleRelationDTO = new UserRoleRelationDTO();
                userRoleRelationDTO.setRoleId(roleId);
                userRoleRelationDTO.setUserId(userId);
                list.add(userRoleRelationDTO);
            }
            list = createUserRoleRelations(list);
            return list.size() > 0;
        }
        int count = userRoleRelationMapper.delete(lambdaQuery);
        return count > 0;
    }

    /**
     * 查询用户已、未关联角色权限
     *
     * @param userId   用户主键
     * @param isRelate true-已关联，false-未关联
     * @return
     */
    @Override
    public List<RoleDTO> findUserRole(String userId, boolean isRelate) {
        return roleMapper.findUserRoles(userId, isRelate);
    }
}
