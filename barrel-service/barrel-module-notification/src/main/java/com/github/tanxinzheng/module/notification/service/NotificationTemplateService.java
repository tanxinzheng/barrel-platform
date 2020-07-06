package com.github.tanxinzheng.module.notification.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.module.notification.domain.dto.NotificationTemplateDTO;
import com.github.tanxinzheng.module.notification.domain.entity.NotificationTemplateDO;

import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-6 23:27:09
 */
public interface NotificationTemplateService {

    /**
     * 新增通知模板
     * @param  notificationTemplateCreate
     * @return NotificationTemplateDTO
     */
    public NotificationTemplateDTO createNotificationTemplate(NotificationTemplateDTO notificationTemplateCreate);

    /**
     * 批量新增通知模板
     * @param notificationTemplate
     * @return List<NotificationTemplate>
     */
    List<NotificationTemplateDTO> createNotificationTemplates(List<NotificationTemplateDTO> notificationTemplate);

    /**
     * 更新通知模板
     * @param   notificationTemplateUpdate
     * @return  boolean
     */
    public boolean updateNotificationTemplate(NotificationTemplateDTO notificationTemplateUpdate);

    /**
     * 主键查询对象
     * @param   id
     * @return  NotificationTemplateDTO
     */
    public NotificationTemplateDTO findById(String id);

    /**
     * 根据code查询对象
     * @param code
     * @return
     */
    public NotificationTemplateDTO findByCode(String code);


    /**
    * 查询集合对象
    * @param queryWrapper
    * @return List<NotificationTemplateDTO>
    */
    public List<NotificationTemplateDTO> findList(QueryWrapper<NotificationTemplateDO> queryWrapper);

    /**
     * 查询通知模板领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    public IPage<NotificationTemplateDTO> findPage(IPage<NotificationTemplateDO> page, QueryWrapper<NotificationTemplateDO> queryWrapper);

    /**
     * 批量删除通知模板
     * @param  ids
     * @return boolean
     */
    public boolean deleteNotificationTemplate(List<String> ids);

    /**
     * 删除通知模板
     * @param  id
     * @return boolean
     */
    public boolean deleteNotificationTemplate(String id);

}
