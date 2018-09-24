package com.github.tanxinzheng.ams.module.appInfo.service;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.ams.module.appInfo.model.AppInfoQuery;
import com.github.tanxinzheng.ams.module.appInfo.model.AppInfoModel;
import com.github.tanxinzheng.ams.module.appInfo.model.AppInfo;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2018-9-11 14:54:49
 * @version 1.0.0
 */
public interface AppInfoService {

    /**
     * 新增应用信息
     * @param  appInfoModel   新增应用信息对象参数
     * @return  AppInfoModel    应用信息领域对象
     */
    public AppInfoModel createAppInfo(AppInfoModel appInfoModel);

    /**
     * 新增应用信息实体对象
     * @param   appInfo 新增应用信息实体对象参数
     * @return  AppInfo 应用信息实体对象
     */
    public AppInfo createAppInfo(AppInfo appInfo);

    /**
    * 批量新增应用信息
    * @param appInfoModels     新增应用信息对象集合参数
    * @return List<AppInfoModel>    应用信息领域对象集合
    */
    List<AppInfoModel> createAppInfos(List<AppInfoModel> appInfoModels);

    /**
    * 更新应用信息
    *
    * @param appInfoModel 更新应用信息对象参数
    * @param appInfoQuery 过滤应用信息对象参数
    */
    public void updateAppInfo(AppInfoModel appInfoModel, AppInfoQuery appInfoQuery);

    /**
     * 更新应用信息
     * @param appInfoModel    更新应用信息对象参数
     */
    public void updateAppInfo(AppInfoModel appInfoModel);

    /**
     * 更新应用信息实体对象
     * @param   appInfo 新增应用信息实体对象参数
     * @return  AppInfo 应用信息实体对象
     */
    public void updateAppInfo(AppInfo appInfo);

    /**
     * 批量删除应用信息
     * @param ids   主键数组
     */
    public void deleteAppInfo(String[] ids);

    /**
     * 删除应用信息
     * @param id   主键
     */
    public void deleteAppInfo(String id);

    /**
     * 查询应用信息领域分页对象（带参数条件）
     * @param appInfoQuery 查询参数
     * @return Page<AppInfoModel>   应用信息参数对象
     */
    public Page<AppInfoModel> getAppInfoModelPage(AppInfoQuery appInfoQuery);

    /**
     * 查询应用信息领域集合对象（带参数条件）
     * @param appInfoQuery 查询参数对象
     * @return List<AppInfoModel> 应用信息领域集合对象
     */
    public List<AppInfoModel> getAppInfoModelList(AppInfoQuery appInfoQuery);

    /**
     * 查询应用信息实体对象
     * @param id 主键
     * @return AppInfo 应用信息实体对象
     */
    public AppInfo getOneAppInfo(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return AppInfoModel 应用信息领域对象
     */
    public AppInfoModel getOneAppInfoModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param appInfoQuery 应用信息查询参数对象
     * @return AppInfoModel 应用信息领域对象
     */
    public AppInfoModel getOneAppInfoModel(AppInfoQuery appInfoQuery);
}
