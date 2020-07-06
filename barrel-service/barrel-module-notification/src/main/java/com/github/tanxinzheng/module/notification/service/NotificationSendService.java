package com.github.tanxinzheng.module.notification.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.module.notification.domain.dto.NotificationSendDTO;
import com.github.tanxinzheng.module.notification.domain.entity.NotificationSendDO;

import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-6 23:27:09
 */
public interface NotificationSendService {

    /**
     * 新增通知发送
     * @param  notificationSendCreate
     * @return NotificationSendDTO
     */
    public NotificationSendDTO createNotificationSend(NotificationSendDTO notificationSendCreate);

    /**
     * 批量新增通知发送
     * @param notificationSend
     * @return List<NotificationSend>
     */
    List<NotificationSendDTO> createNotificationSends(List<NotificationSendDTO> notificationSend);

    /**
     * 更新通知发送
     * @param   notificationSendUpdate
     * @return  boolean
     */
    public boolean updateNotificationSend(NotificationSendDTO notificationSendUpdate);

    /**
     * 主键查询对象
     * @param   id
     * @return  NotificationSendDTO
     */
    public NotificationSendDTO findById(String id);


    /**
    * 查询集合对象
    * @param queryWrapper
    * @return List<NotificationSendDTO>
    */
    public List<NotificationSendDTO> findList(QueryWrapper<NotificationSendDO> queryWrapper);

    /**
     * 查询通知发送领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    public IPage<NotificationSendDTO> findPage(IPage<NotificationSendDO> page, QueryWrapper<NotificationSendDO> queryWrapper);

    /**
     * 批量删除通知发送
     * @param  ids
     * @return boolean
     */
    public boolean deleteNotificationSend(List<String> ids);

    /**
     * 删除通知发送
     * @param  id
     * @return boolean
     */
    public boolean deleteNotificationSend(String id);

}
