package com.github.tanxinzheng.module.authorization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.authorization.domain.dto.PermissionDTO;
import com.github.tanxinzheng.module.authorization.domain.dto.RolePermissionRelationDTO;
import com.github.tanxinzheng.module.authorization.domain.entity.RolePermissionRelationDO;
import com.github.tanxinzheng.module.authorization.mapper.PermissionMapper;
import com.github.tanxinzheng.module.authorization.mapper.RolePermissionRelationMapper;
import com.github.tanxinzheng.module.authorization.service.RolePermissionRelationService;
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
public class RolePermissionRelationServiceImpl extends ServiceImpl<RolePermissionRelationMapper, RolePermissionRelationDO> implements RolePermissionRelationService {

    @Resource
    RolePermissionRelationMapper rolePermissionRelationMapper;

    @Resource
    PermissionMapper permissionMapper;

    /**
     * 新增角色权限关系
     *
     * @param rolePermissionRelationDTO
     * @return RolePermissionRelationResponse
     */
    @Transactional
    @Override
    public RolePermissionRelationDTO createRolePermissionRelation(RolePermissionRelationDTO rolePermissionRelationDTO) {
        AssertValid.notNull(rolePermissionRelationDTO, "rolePermissionRelationDTO参数不能为空");
        RolePermissionRelationDO rolePermissionRelation = rolePermissionRelationDTO.toDO(RolePermissionRelationDO.class);
        boolean isOk = save(rolePermissionRelation);
        if(!isOk){
            return null;
        }
        return BeanCopierUtils.copy(rolePermissionRelation, RolePermissionRelationDTO.class);
    }


    /**
     * 更新角色权限关系
     *
     * @param rolePermissionRelationDTO
     * @return RolePermissionRelationResponse
     */
    @Transactional
    @Override
    public boolean updateRolePermissionRelation(RolePermissionRelationDTO rolePermissionRelationDTO) {
        AssertValid.notNull(rolePermissionRelationDTO, "rolePermissionRelationDTO不能为空");
        RolePermissionRelationDO rolePermissionRelationDO = BeanCopierUtils.copy(rolePermissionRelationDTO, RolePermissionRelationDO.class);
        return updateById(rolePermissionRelationDO);
    }

    /**
     * 批量新增角色权限关系
     * @param rolePermissionRelations
     * @return
     */
    @Transactional
    @Override
    public List<RolePermissionRelationDTO>  createRolePermissionRelations(List<RolePermissionRelationDTO> rolePermissionRelations) {
        AssertValid.notEmpty(rolePermissionRelations, "rolePermissionRelations参数不能为空");
        List<RolePermissionRelationDO> rolePermissionRelationDOList = BeanCopierUtils.copy(rolePermissionRelations, RolePermissionRelationDO.class);
        boolean isOK = saveBatch(rolePermissionRelationDOList);
        if(!isOK){
            return Lists.newArrayList();
        }
        List<String> ids = rolePermissionRelationDOList.stream().map(RolePermissionRelationDO::getId).collect(Collectors.toList());
        List<RolePermissionRelationDO> data = rolePermissionRelationMapper.selectBatchIds(ids);
        return BeanCopierUtils.copy(data, RolePermissionRelationDTO.class);
    }


    /**
     * 主键查询对象
     *
     * @param id
     * @return RolePermissionRelationResponse
     */
    @Override
    public RolePermissionRelationDTO findById(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        RolePermissionRelationDO rolePermissionRelation = getById(id);
        return BeanCopierUtils.copy(rolePermissionRelation, RolePermissionRelationDTO.class);
    }

    /**
    * 查询集合对象
    *
    * @param queryWrapper
    * @return List<RolePermissionRelationDTO>
    */
    @Override
    public List<RolePermissionRelationDTO> findList(QueryWrapper<RolePermissionRelationDO> queryWrapper) {
        return BeanCopierUtils.copy(list(queryWrapper), RolePermissionRelationDTO.class);
    }
    /**
     * 查询角色权限关系领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<RolePermissionRelationDTO> findPage(IPage<RolePermissionRelationDO> page, QueryWrapper<RolePermissionRelationDO> queryWrapper) {
        IPage<RolePermissionRelationDO> data = (Page<RolePermissionRelationDO>) page(page, queryWrapper);
        return BeanCopierUtils.copy(data, RolePermissionRelationDTO.class);
    }

    /**
     * 批量删除角色权限关系
     *
     * @param ids
     * @return int
     */
    @Transactional
    @Override
    public boolean deleteRolePermissionRelation(List<String> ids) {
        AssertValid.notEmpty(ids, "ids参数不能为空");
        return removeByIds(ids);
    }

    /**
     * 删除角色权限关系
     * @param  id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteRolePermissionRelation(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        return removeById(id);
    }

    /**
     * 角色解绑权限
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    @Override
    public boolean relatePermission(String roleId, List<String> permissionIds, boolean isRelate) {
        if(CollectionUtils.isEmpty(permissionIds)){
            return true;
        }
        LambdaQueryWrapper<RolePermissionRelationDO> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(RolePermissionRelationDO::getRoleId, roleId);
        lambdaQuery.in(RolePermissionRelationDO::getPermissionId, permissionIds);
        if(isRelate){
            List<RolePermissionRelationDTO> list = Lists.newArrayList();
            for (String permissionId : permissionIds) {
                RolePermissionRelationDTO rolePermissionRelationDTO = new RolePermissionRelationDTO();
                rolePermissionRelationDTO.setPermissionId(permissionId);
                rolePermissionRelationDTO.setRoleId(roleId);
                list.add(rolePermissionRelationDTO);
            }
            list = createRolePermissionRelations(list);
            return list.size() > 0;
        }
        int count = rolePermissionRelationMapper.delete(lambdaQuery);
        return count > 0;
    }

    /**
     * 查询角色已、未关联权限
     *
     * @param pageNum
     * @param pageSize
     * @param roleId   角色主键
     * @param isRelate true-已关联，false-未关联
     * @return
     */
    @Override
    public IPage<PermissionDTO> findRolePermission(long pageNum, long pageSize, String roleId, boolean isRelate) {
        IPage<PermissionDTO> permissionDTOIPage = new Page<>(pageNum, pageSize);
        return permissionMapper.findRolePermission(permissionDTOIPage, roleId, isRelate);
    }

}
