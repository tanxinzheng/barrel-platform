package com.github.tanxinzheng.ams.module.appInfo.service.impl;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.ams.module.appInfo.mapper.AppInfoMapper;
import com.github.tanxinzheng.ams.module.appInfo.model.AppInfo;
import com.github.tanxinzheng.ams.module.appInfo.model.AppInfoModel;
import com.github.tanxinzheng.ams.module.appInfo.model.AppInfoQuery;
import com.github.tanxinzheng.ams.module.appInfo.service.AppInfoService;
import com.github.tanxinzheng.framework.exception.BusinessException;
import com.github.tanxinzheng.framework.mybatis.page.PageInterceptor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2018-9-11 14:54:49
 * @version 1.0.0
 */
@Service
public class AppInfoServiceImpl implements AppInfoService {

    @Autowired
    AppInfoMapper appInfoMapper;

    /**
     * 新增应用信息
     *
     * @param appInfoModel 新增应用信息对象参数
     * @return AppInfoModel    应用信息领域对象
     */
    @Override
    @Transactional
    public AppInfoModel createAppInfo(AppInfoModel appInfoModel) {
        AppInfo appInfo = createAppInfo(appInfoModel.getEntity());
        if(appInfo != null){
            return getOneAppInfoModel(appInfo.getId());
        }
        return null;
    }

    /**
     * 新增应用信息实体对象
     *
     * @param appInfo 新增应用信息实体对象参数
     * @return AppInfo 应用信息实体对象
     */
    @Override
    @Transactional
    public AppInfo createAppInfo(AppInfo appInfo) {
        appInfoMapper.insertSelective(appInfo);
        return appInfo;
    }

    /**
    * 批量新增应用信息
    *
    * @param appInfoModels 新增应用信息对象集合参数
    * @return List<AppInfoModel>    应用信息领域对象集合
    */
    @Override
    @Transactional
    public List<AppInfoModel> createAppInfos(List<AppInfoModel> appInfoModels) {
        List<AppInfoModel> appInfoModelList = null;
        for (AppInfoModel appInfoModel : appInfoModels) {
            appInfoModel = createAppInfo(appInfoModel);
            if(appInfoModel != null){
                if(appInfoModelList == null){
                    appInfoModelList = new ArrayList<>();
                }
                appInfoModelList.add(appInfoModel);
            }
        }
        return appInfoModelList;
    }

    /**
    * 更新应用信息
    *
    * @param appInfoModel 更新应用信息对象参数
    * @param appInfoQuery 过滤应用信息对象参数
    */
    @Override
    @Transactional
    public void updateAppInfo(AppInfoModel appInfoModel, AppInfoQuery appInfoQuery) {
        appInfoMapper.updateSelectiveByQuery(appInfoModel.getEntity(), appInfoQuery);
    }

    /**
     * 更新应用信息
     *
     * @param appInfoModel 更新应用信息对象参数
     */
    @Override
    @Transactional
    public void updateAppInfo(AppInfoModel appInfoModel) {
        updateAppInfo(appInfoModel.getEntity());
    }

    /**
     * 更新应用信息实体对象
     *
     * @param appInfo 新增应用信息实体对象参数
     * @return AppInfo 应用信息实体对象
     */
    @Override
    @Transactional
    public void updateAppInfo(AppInfo appInfo) {
        appInfoMapper.updateSelective(appInfo);
    }

    /**
     * 删除应用信息
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteAppInfo(String[] ids) {
        appInfoMapper.deletesByPrimaryKey(Arrays.asList(ids));
    }

    /**
    * 删除应用信息
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteAppInfo(String id) {
        appInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询应用信息领域分页对象（带参数条件）
     *
     * @param appInfoQuery 查询参数
     * @return Page<AppInfoModel>   应用信息参数对象
     */
    @Override
    public Page<AppInfoModel> getAppInfoModelPage(AppInfoQuery appInfoQuery) {
        PageInterceptor.startPage(appInfoQuery);
        appInfoMapper.selectModel(appInfoQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询应用信息领域集合对象（带参数条件）
     *
     * @param appInfoQuery 查询参数对象
     * @return List<AppInfoModel> 应用信息领域集合对象
     */
    @Override
    public List<AppInfoModel> getAppInfoModelList(AppInfoQuery appInfoQuery) {
        return appInfoMapper.selectModel(appInfoQuery);
    }

    /**
     * 查询应用信息实体对象
     *
     * @param id 主键
     * @return AppInfo 应用信息实体对象
     */
    @Override
    public AppInfo getOneAppInfo(String id) {
        return appInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return AppInfoModel 应用信息领域对象
     */
    @Override
    public AppInfoModel getOneAppInfoModel(String id) {
        return appInfoMapper.selectModelByPrimaryKey(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param appInfoQuery 应用信息查询参数对象
     * @return AppInfoModel 应用信息领域对象
     */
    @Override
    public AppInfoModel getOneAppInfoModel(AppInfoQuery appInfoQuery) {
        List<AppInfoModel> appInfoModelList = appInfoMapper.selectModel(appInfoQuery);
        if(CollectionUtils.isEmpty(appInfoModelList)){
            return null;
        }
        if(appInfoModelList.size() > 1){
            throw new BusinessException();
        }
        return appInfoModelList.get(0);
    }
}
