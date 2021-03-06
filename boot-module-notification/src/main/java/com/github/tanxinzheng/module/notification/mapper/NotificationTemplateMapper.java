package com.github.tanxinzheng.module.notification.mapper;

import com.github.tanxinzheng.module.notification.model.NotificationTemplate;
import com.github.tanxinzheng.module.notification.model.NotificationTemplateModel;
import com.github.tanxinzheng.module.notification.model.NotificationTemplateQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:47
 * @version 1.0.0
 */
@Mapper
public interface NotificationTemplateMapper {

    List<NotificationTemplate> select(NotificationTemplateQuery notificationTemplateQuery);

    List<NotificationTemplateModel> selectModel(NotificationTemplateQuery notificationTemplateQuery);

    NotificationTemplate selectByPrimaryKey(String primaryKey);

    NotificationTemplateModel selectModelByPrimaryKey(String primaryKey);

    int deleteByPrimaryKey(String primaryKey);

    int deletesByPrimaryKey(@Param("ids") List<String> primaryKeys);

    int insertSelective(NotificationTemplate record);

    int updateSelective(NotificationTemplate record);

    int updateSelectiveByQuery(@Param("record") NotificationTemplate record, @Param("query") NotificationTemplateQuery example);
}
