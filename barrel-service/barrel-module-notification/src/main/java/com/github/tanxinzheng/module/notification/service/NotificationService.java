package com.github.tanxinzheng.module.notification.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.module.notification.domain.dto.NotificationDTO;
import com.github.tanxinzheng.module.notification.domain.entity.NotificationDO;

import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-6 23:27:09
 */
public interface NotificationService {

    /**
     * 新增通知
     * @param  notificationCreate
     * @return NotificationDTO
     */
    public NotificationDTO createNotification(NotificationDTO notificationCreate);

    /**
     * 批量新增通知
     * @param notification
     * @return List<Notification>
     */
    List<NotificationDTO> createNotifications(List<NotificationDTO> notification);

    /**
     * 更新通知
     * @param   notificationUpdate
     * @return  boolean
     */
    public boolean updateNotification(NotificationDTO notificationUpdate);

    /**
     * 主键查询对象
     * @param   id
     * @return  NotificationDTO
     */
    public NotificationDTO findById(String id);


    /**
    * 查询集合对象
    * @param queryWrapper
    * @return List<NotificationDTO>
    */
    public List<NotificationDTO> findList(QueryWrapper<NotificationDO> queryWrapper);

    /**
     * 查询通知领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    public IPage<NotificationDTO> findPage(IPage<NotificationDO> page, QueryWrapper<NotificationDO> queryWrapper);

    /**
     * 批量删除通知
     * @param  ids
     * @return boolean
     */
    public boolean deleteNotification(List<String> ids);

    /**
     * 删除通知
     * @param  id
     * @return boolean
     */
    public boolean deleteNotification(String id);

}
