package com.github.tanxinzheng.module.authorization.controller;

import com.github.tanxinzheng.framework.web.model.RestResponse;
import com.github.tanxinzheng.module.authorization.constant.PermissionAction;
import com.github.tanxinzheng.module.authorization.model.PermissionCreate;
import com.github.tanxinzheng.module.authorization.model.PermissionModel;
import com.github.tanxinzheng.module.authorization.model.PermissionQuery;
import com.github.tanxinzheng.module.authorization.service.PermissionService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

//import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@Api(tags = {"权限管理"})
@RestController
@RequestMapping(value = "/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 权限列表.
     * @param   permissionQuery    权限查询参数对象
     * @return  Page<PermissionModel> 权限领域分页对象
     */
    @ApiOperation(value = "查询权限列表", tags = "查询权限列表")
    @RequestMapping(method = GET)
    public List<PermissionModel> getPermissionList(final PermissionQuery permissionQuery) {
        return permissionService.getPermissionModelList(permissionQuery);
    }

    /**
     * 查询单个权限.
     * @param   id  主键
     * @return  PermissionModel   权限领域对象
     */
    @ApiOperation(value = "查询权限")
    @RequestMapping(value = "/{id}", method = GET)
    public PermissionModel getPermissionById(@PathVariable(value = "id") String id) {
        return permissionService.getOnePermissionModel(id);
    }

    /**
     * 新增权限
     * @param   permissionCreate  新增对象参数
     * @return  PermissionModel   权限领域对象
     */
    @ApiOperation(value = "新增权限")
    @RequestMapping(method = POST)
    public void createPermission(@RequestBody @Valid PermissionCreate permissionCreate) {
        List<PermissionModel> list = Lists.newArrayList();
        for (PermissionAction action : PermissionAction.values()) {
            PermissionModel permissionModel = new PermissionModel();
            permissionModel.setPermissionUrl(permissionCreate.getPermissionUrl());
            permissionModel.setDescription(permissionCreate.getDescription() + ":" + action.getDesc());
            permissionModel.setActive(permissionCreate.getActive());
            permissionModel.setPermissionAction(action.name());
            permissionModel.setPermissionKey(permissionCreate.getPermissionGroup().toUpperCase() + ":" + action.name());
            list.add(permissionModel);
        }
        permissionService.createPermissions(list);
    }

    /**
     * 更新权限
     * @param id    主键
     * @param permissionModel  更新对象参数
     * @return  PermissionModel   权限领域对象
     */
    @ApiOperation(value = "更新权限")
    @RequestMapping(value = "/{id}", method = PUT)
    public PermissionModel updatePermission(@PathVariable(value = "id") String id,
                           @RequestBody @Valid PermissionModel permissionModel){
        if(StringUtils.isNotBlank(id)){
            permissionModel.setId(id);
        }
        permissionService.updatePermission(permissionModel);
        return permissionService.getOnePermissionModel(id);
    }

    /**
     *  删除权限
     * @param id    主键
     */
    @ApiOperation(value = "删除单个权限")
    @RequestMapping(value = "/{id}", method = DELETE)
    public void deletePermission(@PathVariable(value = "id") String id){
        permissionService.deletePermission(id);
    }

    /**
     *  删除权限
     * @param permissionQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除权限")
    @RequestMapping(method = DELETE)
    public void deletePermissions(final PermissionQuery permissionQuery){
        permissionService.deletePermission(permissionQuery.getIds());
    }
//
//    /**
//     * 下载Excel模板
//     */
//    @RequestMapping(value="/template", method = GET)
//    public void downloadTemplate(HttpServletRequest request,
//                                 HttpServletResponse response) {
//        List<PermissionModel> list = Lists.newArrayList();
//        ExcelUtils.export(request, response, PermissionModel.class, list, "权限_模板");
//    }
//
//    /**
//     * 导出Excel
//     * @param permissionQuery    查询参数对象
//     */
//    @ApiOperation(value = "导出权限")
//    @RequestMapping(value="/export", method = GET)
//    public void exportExcel(HttpServletRequest request,
//                                           HttpServletResponse response,
//                                           PermissionQuery permissionQuery) {
//        List<PermissionModel> list = permissionService.getPermissionModelList(permissionQuery);
//        ExcelUtils.export(request, response, PermissionModel.class, list, "权限");
//    }
//
//    /**
//     * 导入Excel
//     * @param file
//     */
//    @ApiOperation(value = "批量导入权限")
//    @RequestMapping(value="/import", method = POST)
//    public void importExcel(@LoginUser CurrentLoginUser loginUser, @RequestParam("file") MultipartFile file) throws IOException {
//        List<PermissionModel> list = ExcelUtils.transform(file, PermissionModel.class);
//        if(CollectionUtils.isEmpty(list)){
//            return;
//        }
//        list.stream().forEach(permissionModel -> {
//            permissionModel.setUpdatedUserId(loginUser.getId());
//            permissionModel.setCreatedUserId(loginUser.getId());
//        });
//        permissionService.createPermissions(list);
//    }


    /**
     * 获取所有RequestMappingInfo
     * @param request
     * @return
     */
    @ApiOperation(value = "获取所有未纳入权限控制资源")
    @GetMapping(value = "/sync")
    public RestResponse syncAll(@RequestParam(value = "group", required = false) String swaggerGroup,
                               HttpServletRequest request) {
        permissionService.autoInitPermissions(swaggerGroup, "TEST");
        return RestResponse.success("同步成功");
    }



}
