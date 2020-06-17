package com.github.tanxinzheng.module.authorization.service.impl;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.exception.BusinessException;
import com.github.tanxinzheng.framework.mybatis.page.PageInterceptor;
import com.github.tanxinzheng.framework.utils.UUIDGenerator;
import com.github.tanxinzheng.module.authorization.mapper.GroupPermissionMapper;
import com.github.tanxinzheng.module.authorization.mapper.PermissionMapper;
import com.github.tanxinzheng.module.authorization.model.Permission;
import com.github.tanxinzheng.module.authorization.model.PermissionModel;
import com.github.tanxinzheng.module.authorization.model.PermissionQuery;
import com.github.tanxinzheng.module.authorization.service.PermissionService;
import com.google.common.collect.Lists;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    GroupPermissionMapper groupPermissionMapper;

    /**
     * 新增权限
     *
     * @param permissionModel 新增权限对象参数
     * @return PermissionModel    权限领域对象
     */
    @Override
    @Transactional
    public PermissionModel createPermission(PermissionModel permissionModel) {
        Permission permission = createPermission(permissionModel.getEntity());
        if(permission != null){
            return getOnePermissionModel(permission.getId());
        }
        return null;
    }

    /**
     * 新增权限实体对象
     *
     * @param permission 新增权限实体对象参数
     * @return Permission 权限实体对象
     */
    @Override
    @Transactional
    public Permission createPermission(Permission permission) {
        permissionMapper.insertSelective(permission);
        return permission;
    }

    /**
    * 批量新增权限
    *
    * @param permissionModels 新增权限对象集合参数
    * @return List<PermissionModel>    权限领域对象集合
    */
    @Override
    @Transactional
    public List<PermissionModel> createPermissions(List<PermissionModel> permissionModels) {
        for (PermissionModel permissionModel : permissionModels) {
            permissionModel.setCreatedTime(new Date());
            permissionModel.setUpdatedTime(new Date());
            permissionMapper.insertSelective(permissionModel.getEntity());
        }
        return permissionModels;
    }

    /**
    * 更新权限
    *
    * @param permissionModel 更新权限对象参数
    * @param permissionQuery 过滤权限对象参数
    */
    @Override
    @Transactional
    public void updatePermission(PermissionModel permissionModel, PermissionQuery permissionQuery) {
        permissionMapper.updateSelectiveByQuery(permissionModel.getEntity(), permissionQuery);
    }

    /**
     * 更新权限
     *
     * @param permissionModel 更新权限对象参数
     */
    @Override
    @Transactional
    public void updatePermission(PermissionModel permissionModel) {
        updatePermission(permissionModel.getEntity());
    }

    /**
     * 更新权限实体对象
     *
     * @param permission 新增权限实体对象参数
     * @return Permission 权限实体对象
     */
    @Override
    @Transactional
    public void updatePermission(Permission permission) {
        permissionMapper.updateSelective(permission);
    }

    /**
     * 删除权限
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deletePermission(String[] ids) {
        long count = groupPermissionMapper.countGroupPermissions(null, Arrays.asList(ids));
        if(count > 0){
            throw new BusinessException("所选择删除的权限已绑定用户组，请移除绑定关系后再删除");
        }
        permissionMapper.deletesByPrimaryKey(Arrays.asList(ids));
    }

    /**
    * 删除权限
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deletePermission(String id) {
        if(StringUtils.isBlank(id)){
            return;
        }
        List<String> ids = Lists.newArrayList();
        ids.add(id);
        long count = groupPermissionMapper.countGroupPermissions(null, ids);
        if(count > 0){
            throw new BusinessException("所选择删除的权限已绑定用户组，请移除绑定关系后再删除");
        }
        permissionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询权限领域分页对象（带参数条件）
     *
     * @param permissionQuery 查询参数
     * @return Page<PermissionModel>   权限参数对象
     */
    @Override
    public Page<PermissionModel> getPermissionModelPage(PermissionQuery permissionQuery) {
        PageInterceptor.startPage(permissionQuery);
        permissionMapper.selectModel(permissionQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询权限领域集合对象（带参数条件）
     *
     * @param permissionQuery 查询参数对象
     * @return List<PermissionModel> 权限领域集合对象
     */
    @Override
    public List<PermissionModel> getPermissionModelList(PermissionQuery permissionQuery) {
        return permissionMapper.selectModel(permissionQuery);
    }

    /**
     * 查询权限实体对象
     *
     * @param id 主键
     * @return Permission 权限实体对象
     */
    @Override
    public Permission getOnePermission(String id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return PermissionModel 权限领域对象
     */
    @Override
    public PermissionModel getOnePermissionModel(String id) {
        return permissionMapper.selectModelByPrimaryKey(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param permissionQuery 权限查询参数对象
     * @return PermissionModel 权限领域对象
     */
    @Override
    public PermissionModel getOnePermissionModel(PermissionQuery permissionQuery) {
        List<PermissionModel> permissionModelList = permissionMapper.selectModel(permissionQuery);
        if(CollectionUtils.isEmpty(permissionModelList)){
            return null;
        }
        if(permissionModelList.size() > 1){
            throw new BusinessException();
        }
        return permissionModelList.get(0);
    }

    @Autowired
    private DocumentationCache documentationCache;

    @Autowired
    private ServiceModelToSwagger2Mapper mapper;

    @Transactional
    @Override
    public void autoInitPermissions(String swaggerGroup, String updatedBy) {
        String groupName = Optional.ofNullable(swaggerGroup).orElse(Docket.DEFAULT_GROUP_NAME);
        Documentation documentation = documentationCache.documentationByGroup(groupName);
        List<PermissionModel> newList = Lists.newArrayList();
        if (documentation == null) {
            return;
        }
        Swagger swagger = mapper.mapDocumentation(documentation);
        Map<String, Path> map = swagger.getPaths();
        for (Map.Entry<String, Path> stringPathEntry : map.entrySet()) {
            Path path = stringPathEntry.getValue();
            Map<HttpMethod, Operation> operationMap = path.getOperationMap();
            for (HttpMethod httpMethod : operationMap.keySet()) {
                PermissionModel permission = new PermissionModel();
                permission.setId(UUIDGenerator.getInstance().getUUID());
                permission.setPermissionUrl(stringPathEntry.getKey());
                permission.setPermissionAction(httpMethod.name().toUpperCase());
                permission.setDescription(operationMap.get(httpMethod).getSummary());
                permission.setPermissionKey(httpMethod.name().toUpperCase() + ":" + stringPathEntry.getKey());
                permission.setActive(Boolean.TRUE);
                permission.setCreatedUserId(updatedBy);
                permission.setUpdatedUserId(updatedBy);
                newList.add(permission);
            }
        }
        PermissionQuery query = new PermissionQuery();
        List<PermissionModel> oldList = permissionMapper.selectModel(query);
        Map<String, PermissionModel> oldMap = oldList.stream().collect(Collectors.toMap(PermissionModel::getPermissionKey, Function.identity()));
        Map<String, PermissionModel> newMap = newList.stream().collect(Collectors.toMap(PermissionModel::getPermissionKey, Function.identity()));
        List<PermissionModel> oldUpdateList = Lists.newArrayList();
        List<PermissionModel> newInsertList = Lists.newArrayList();
        newMap.forEach((key, newPermission) -> {
            PermissionModel oldPermission = oldMap.get(key);
            Optional<PermissionModel> model = Optional.of(oldPermission);
            if(!model.isPresent()){
                // 历史权限资源不存在
                newInsertList.add(newPermission);
            }else if(!newPermission.getDescription().equals(oldPermission.getDescription())
                    || !newPermission.getPermissionAction().equals(oldPermission.getPermissionAction())
                    || !newPermission.getPermissionUrl().equals(oldPermission.getPermissionUrl())){
                // 历史权限资源已存在
                oldPermission.setPermissionUrl(newPermission.getPermissionUrl());
                oldPermission.setDescription(newPermission.getDescription());
                oldPermission.setPermissionAction(newPermission.getPermissionAction());
                oldPermission.setUpdatedTime(new Date());
                oldPermission.setUpdatedUserId(newPermission.getUpdatedUserId());
                oldUpdateList.add(oldPermission);
            }
        });
        //
        List<String> oldDeleteList = Lists.newArrayList();
        oldMap.forEach((key, oldPermission) -> {
            PermissionModel newPermission = newMap.get(key);
            if(newPermission == null){
                oldDeleteList.add(oldPermission.getId());
            }
        });
        if(CollectionUtils.isNotEmpty(newInsertList)){
            permissionMapper.insertByBatch(newInsertList);
        }
        if(CollectionUtils.isNotEmpty(oldDeleteList)){
            permissionMapper.deletesByPrimaryKey(oldDeleteList);
        }
        if(CollectionUtils.isNotEmpty(oldUpdateList)){
            oldUpdateList.stream().forEach(permissionModel -> {
                permissionMapper.updateSelective(permissionModel.getEntity());
            });
        }
    }
}
