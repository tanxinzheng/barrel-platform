package com.github.tanxinzheng.ams.module.appInfo.controller;

import io.swagger.annotations.ApiOperation;
import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.logger.ActionLog;
import com.github.tanxinzheng.ams.module.appInfo.model.AppInfoQuery;
import com.github.tanxinzheng.ams.module.appInfo.model.AppInfoModel;
import com.github.tanxinzheng.ams.module.appInfo.service.AppInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.lang3.StringUtils;
import javax.validation.Valid;

/**
 * @author  tanxinzheng
 * @date    2018-9-11 14:54:49
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/appInfo")
public class AppInfoController {

    private static Logger logger = LoggerFactory.getLogger(AppInfoController.class);

    @Autowired
    AppInfoService appInfoService;

    /**
     * 应用信息列表
     * @param   appInfoQuery    应用信息查询参数对象
     * @return  Page<AppInfoModel> 应用信息领域分页对象
     */
    @ApiOperation(value = "查询应用信息列表")
    @ActionLog(actionName = "查询应用信息列表")
    @RequestMapping(method = RequestMethod.GET)
    public Page<AppInfoModel> getAppInfoList(AppInfoQuery appInfoQuery){
        return appInfoService.getAppInfoModelPage(appInfoQuery);
    }

    /**
     * 查询单个应用信息
     * @param   id  主键
     * @return  AppInfoModel   应用信息领域对象
     */
    @ApiOperation(value = "查询应用信息")
    @ActionLog(actionName = "查询应用信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AppInfoModel getAppInfoById(@PathVariable(value = "id") String id){
        return appInfoService.getOneAppInfoModel(id);
    }

    /**
     * 新增应用信息
     * @param   appInfoModel  新增对象参数
     * @return  AppInfoModel   应用信息领域对象
     */
    @ApiOperation(value = "新增应用信息")
    @ActionLog(actionName = "新增应用信息")
    @RequestMapping(method = RequestMethod.POST)
    public AppInfoModel createAppInfo(@RequestBody @Valid AppInfoModel appInfoModel) {
        return appInfoService.createAppInfo(appInfoModel);
    }

    /**
     * 更新应用信息
     * @param id    主键
     * @param appInfoModel  更新对象参数
     * @return  AppInfoModel   应用信息领域对象
     */
    @ApiOperation(value = "更新应用信息")
    @ActionLog(actionName = "更新应用信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public AppInfoModel updateAppInfo(@PathVariable(value = "id") String id,
                           @RequestBody @Valid AppInfoModel appInfoModel){
        if(StringUtils.isNotBlank(id)){
            appInfoModel.setId(id);
        }
        appInfoService.updateAppInfo(appInfoModel);
        return appInfoService.getOneAppInfoModel(id);
    }

    /**
     *  删除应用信息
     * @param id    主键
     */
    @ApiOperation(value = "删除单个应用信息")
    @ActionLog(actionName = "删除单个应用信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAppInfo(@PathVariable(value = "id") String id){
        appInfoService.deleteAppInfo(id);
    }

    /**
     *  删除应用信息
     * @param appInfoQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除应用信息")
    @ActionLog(actionName = "批量删除应用信息")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAppInfos(AppInfoQuery appInfoQuery){
        appInfoService.deleteAppInfo(appInfoQuery.getIds());
    }


}
