package com.github.tanxinzheng.module.notification.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.tanxinzheng.module.notification.domain.entity.NotificationReceiveDO;
import com.github.tanxinzheng.module.notification.model.NotificationModel;
import com.github.tanxinzheng.module.notification.model.NotificationQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-6 23:27:09
 */
@Mapper
public interface NotificationReceiveMapper extends BaseMapper<NotificationReceiveDO> {

    List<NotificationModel> selectNotification(NotificationQuery notificationQuery);
}