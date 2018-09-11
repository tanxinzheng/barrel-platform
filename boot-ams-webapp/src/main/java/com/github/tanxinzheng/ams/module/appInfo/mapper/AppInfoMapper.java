package com.github.tanxinzheng.ams.module.appInfo.mapper;

import com.github.tanxinzheng.ams.module.appInfo.model.AppInfo;
import com.github.tanxinzheng.ams.module.appInfo.model.AppInfoModel;
import com.github.tanxinzheng.ams.module.appInfo.model.AppInfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2018-9-11 14:54:49
 * @version 1.0.0
 */
public interface AppInfoMapper {

    List<AppInfo> select(AppInfoQuery appInfoQuery);

    List<AppInfoModel> selectModel(AppInfoQuery appInfoQuery);

    AppInfo selectByPrimaryKey(String primaryKey);

    AppInfoModel selectModelByPrimaryKey(String primaryKey);

    int deleteByPrimaryKey(String primaryKey);

    int deletesByPrimaryKey(@Param("ids") List<String> primaryKeys);

    int insertSelective(AppInfo record);

    int updateSelective(AppInfo record);

    int updateSelectiveByQuery(@Param("record") AppInfo record, @Param("query") AppInfoQuery example);
}
