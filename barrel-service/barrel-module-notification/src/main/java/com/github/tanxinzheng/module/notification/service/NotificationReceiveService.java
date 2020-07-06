package com.github.tanxinzheng.module.notification.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.module.notification.domain.dto.NotificationReceiveDTO;
import com.github.tanxinzheng.module.notification.domain.entity.NotificationReceiveDO;

import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-6 23:27:09
 */
public interface NotificationReceiveService {

    /**
     * 新增通知接收
     * @param  notificationReceiveCreate
     * @return NotificationReceiveDTO
     */
    public NotificationReceiveDTO createNotificationReceive(NotificationReceiveDTO notificationReceiveCreate);

    /**
     * 批量新增通知接收
     * @param notificationReceive
     * @return List<NotificationReceive>
     */
    List<NotificationReceiveDTO> createNotificationReceives(List<NotificationReceiveDTO> notificationReceive);

    /**
     * 更新通知接收
     * @param   notificationReceiveUpdate
     * @return  boolean
     */
    public boolean updateNotificationReceive(NotificationReceiveDTO notificationReceiveUpdate);

    /**
     * 主键查询对象
     * @param   id
     * @return  NotificationReceiveDTO
     */
    public NotificationReceiveDTO findById(String id);


    /**
    * 查询集合对象
    * @param queryWrapper
    * @return List<NotificationReceiveDTO>
    */
    public List<NotificationReceiveDTO> findList(QueryWrapper<NotificationReceiveDO> queryWrapper);

    /**
     * 查询通知接收领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    public IPage<NotificationReceiveDTO> findPage(IPage<NotificationReceiveDO> page, QueryWrapper<NotificationReceiveDO> queryWrapper);

    /**
     * 批量删除通知接收
     * @param  ids
     * @return boolean
     */
    public boolean deleteNotificationReceive(List<String> ids);

    /**
     * 删除通知接收
     * @param  id
     * @return boolean
     */
    public boolean deleteNotificationReceive(String id);

}
