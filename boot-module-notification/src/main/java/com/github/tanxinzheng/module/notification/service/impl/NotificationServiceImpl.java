package com.github.tanxinzheng.module.notification.service.impl;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.exception.BusinessException;
import com.github.tanxinzheng.framework.mybatis.page.PageInterceptor;
import com.github.tanxinzheng.module.notification.mapper.NotificationMapper;
import com.github.tanxinzheng.module.notification.model.*;
import com.github.tanxinzheng.module.notification.service.NotificationReceiveService;
import com.github.tanxinzheng.module.notification.service.NotificationSendService;
import com.github.tanxinzheng.module.notification.service.NotificationService;
import com.github.tanxinzheng.module.notification.service.NotificationTemplateService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationMapper notificationMapper;

    /**
     * 新增通知
     *
     * @param notificationModel 新增通知对象参数
     * @return NotificationModel    通知领域对象
     */
    @Override
    @Transactional
    public NotificationModel createNotification(NotificationModel notificationModel) {
        Notification notification = createNotification(notificationModel.getEntity());
        if(notification != null){
            return getOneNotificationModel(notification.getId());
        }
        return null;
    }

    /**
     * 新增通知实体对象
     *
     * @param notification 新增通知实体对象参数
     * @return Notification 通知实体对象
     */
    @Override
    @Transactional
    public Notification createNotification(Notification notification) {
        notificationMapper.insertSelective(notification);
        return notification;
    }

    /**
    * 批量新增通知
    *
    * @param notificationModels 新增通知对象集合参数
    * @return List<NotificationModel>    通知领域对象集合
    */
    @Override
    @Transactional
    public List<NotificationModel> createNotifications(List<NotificationModel> notificationModels) {
        List<NotificationModel> notificationModelList = null;
        for (NotificationModel notificationModel : notificationModels) {
            notificationModel = createNotification(notificationModel);
            if(notificationModel != null){
                if(notificationModelList == null){
                    notificationModelList = new ArrayList<>();
                }
                notificationModelList.add(notificationModel);
            }
        }
        return notificationModelList;
    }

    /**
    * 更新通知
    *
    * @param notificationModel 更新通知对象参数
    * @param notificationQuery 过滤通知对象参数
    */
    @Override
    @Transactional
    public void updateNotification(NotificationModel notificationModel, NotificationQuery notificationQuery) {
        notificationMapper.updateSelectiveByQuery(notificationModel.getEntity(), notificationQuery);
    }

    /**
     * 更新通知
     *
     * @param notificationModel 更新通知对象参数
     */
    @Override
    @Transactional
    public void updateNotification(NotificationModel notificationModel) {
        updateNotification(notificationModel.getEntity());
    }

    /**
     * 更新通知实体对象
     *
     * @param notification 新增通知实体对象参数
     * @return Notification 通知实体对象
     */
    @Override
    @Transactional
    public void updateNotification(Notification notification) {
        notificationMapper.updateSelective(notification);
    }

    /**
     * 删除通知
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteNotification(String[] ids) {
        notificationMapper.deletesByPrimaryKey(Arrays.asList(ids));
    }

    /**
    * 删除通知
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteNotification(String id) {
        notificationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 通知状态统计
     *
     * @param notificationQuery
     */
    @Override
    public List<NotificationStateCount> countNotificationState(NotificationQuery notificationQuery) {
        return notificationMapper.countNotificationState(notificationQuery);
    }

    /**
     * 查询通知领域分页对象（带参数条件）
     *
     * @param notificationQuery 查询参数
     * @return Page<NotificationModel>   通知参数对象
     */
    @Override
    public Page<NotificationModel> getNotificationModelPage(NotificationQuery notificationQuery) {
        PageInterceptor.startPage(notificationQuery);
        notificationMapper.selectModel(notificationQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询通知领域集合对象（带参数条件）
     *
     * @param notificationQuery 查询参数对象
     * @return List<NotificationModel> 通知领域集合对象
     */
    @Override
    public List<NotificationModel> getNotificationModelList(NotificationQuery notificationQuery) {
        return notificationMapper.selectModel(notificationQuery);
    }

    /**
     * 查询通知实体对象
     *
     * @param id 主键
     * @return Notification 通知实体对象
     */
    @Override
    public Notification getOneNotification(String id) {
        return notificationMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return NotificationModel 通知领域对象
     */
    @Override
    public NotificationModel getOneNotificationModel(String id) {
        return notificationMapper.selectModelByPrimaryKey(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param notificationQuery 通知查询参数对象
     * @return NotificationModel 通知领域对象
     */
    @Override
    public NotificationModel getOneNotificationModel(NotificationQuery notificationQuery) {
        List<NotificationModel> notificationModelList = notificationMapper.selectModel(notificationQuery);
        if(CollectionUtils.isEmpty(notificationModelList)){
            return null;
        }
        if(notificationModelList.size() > 1){
            throw new BusinessException();
        }
        return notificationModelList.get(0);
    }

    @Autowired
    NotificationReceiveService notificationReceiveService;

    @Override
    public void read(String id, String receiverId) {
        NotificationReceiveQuery query = new NotificationReceiveQuery();
        query.setNotificationId(id);
        query.setReceiverId(receiverId);
        NotificationReceiveModel notificationReceiveModel = notificationReceiveService.getOneNotificationReceiveModel(query);
        if(notificationReceiveModel == null) {
            notificationReceiveModel = new NotificationReceiveModel();
            notificationReceiveModel.setDataState(NotificationDataState.READ.name());
            notificationReceiveModel.setNotificationId(id);
            notificationReceiveModel.setReceiveTime(new Date());
            notificationReceiveModel.setReceiver(receiverId);
            notificationReceiveModel.setNotificationId(id);
            notificationReceiveService.createNotificationReceive(notificationReceiveModel);
        }else {
            NotificationReceive notificationReceive = new NotificationReceive();
            notificationReceive.setId(notificationReceiveModel.getId());
            notificationReceive.setDataState(NotificationDataState.READ.name());
            notificationReceive.setReceiveTime(new Date());
            notificationReceive.setUpdatedTime(new Date());
            notificationReceiveService.updateNotificationReceive(notificationReceive);
        }
    }

    @Autowired
    NotificationTemplateService notificationTemplateService;
    @Autowired
    NotificationSendService notificationSendService;

    @Transactional
    @Override
    public NotificationModel send(SendNotification sendNotification) {
        // 构建消息
        NotificationModel notification = new NotificationModel();
        notification.setTitle(sendNotification.getTitle());
        notification.setBody(sendNotification.getBody());
        notification.setExpireTime(sendNotification.getExpireTime());
        notification.setNotificationType(sendNotification.getNotificationType());
        // 转译模板内容
        convertByTemplate(sendNotification.getTemplateCode(), sendNotification.getTemplateData(), notification);
        notification = createNotification(notification);
//        if(ArrayUtils.isNotEmpty(sendNotification.getReceiveGroup())){
//            UserGroupQuery userGroupQuery = new UserGroupQuery();
//            userGroupQuery.setGroupIds(sendNotification.getReceiveGroup());
//            List<UserGroupModel> userGroupModelList = userGroupService.getUserGroupModelList(userGroupQuery);
//            if(CollectionUtils.isNotEmpty(userGroupModelList)){
//                List<String> users = Lists.newArrayList();
//                userGroupModelList.stream().forEach(userGroupModel -> {
//                    users.add(userGroupModel.getUserId());
//                });
//                sendNotification.setReceiver(ArrayUtils.addAll(sendNotification.getReceiver(), users.toArray(new String[]{})));
//            }
//        }
        if(ArrayUtils.isEmpty(sendNotification.getReceiver())){
            throw new BusinessException("接收人不能为空");
        }
        NotificationSend notificationSend = new NotificationSend();
        notificationSend.setNotificationId(notification.getId());
        notificationSend.setDataState(NotificationDataState.WAITING.name());
        notificationSend.setSender(sendNotification.getSender());
        notificationSend.setSendTime(new Date());
        notificationSendService.createNotificationSend(notificationSend);
        for (String receiver : sendNotification.getReceiver()) {
            NotificationReceive notificationReceive = new NotificationReceive();
            notificationReceive.setDataState(NotificationDataState.UNREAD.name());
            notificationReceive.setNotificationId(notification.getId());
            notificationReceive.setReceiver(receiver);
            notificationReceive.setReceiveTime(new Date());
            notificationReceiveService.createNotificationReceive(notificationReceive);
        }
        return notification;
    }

    /**
     *
     * @param templateCode
     * @param data
     * @param notificationModel
     */
    private void convertByTemplate(String templateCode, Map<String, Object> data, NotificationModel notificationModel){
        NotificationTemplateQuery notificationTemplateQuery = new NotificationTemplateQuery();
        notificationTemplateQuery.setTemplateCode(templateCode);
        NotificationTemplateModel templateModel = notificationTemplateService.getOneNotificationTemplateModel(notificationTemplateQuery);
        if(templateModel == null){
            return;
        }
        notificationModel.setTemplateId(templateModel.getId());
        String title = processTemplate(templateModel.getTemplateTitle(), data);
        String body = processTemplate(templateModel.getTemplateBody(), data);
        notificationModel.setTitle(title);
        notificationModel.setBody(body);
    }

    /**
     * 处理模板
     * @param templateContent
     * @param data
     * @return
     */
    private String processTemplate(String templateContent, Map data){
        Configuration cfg = new Configuration();
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        stringLoader.putTemplate("myTemplate", templateContent);
        cfg.setTemplateLoader(stringLoader);
        cfg.setEncoding(Locale.getDefault(), "UTF-8");
        cfg.setClassicCompatible(true);
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setNumberFormat("#");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        try {
            Template template = cfg.getTemplate("myTemplate","utf-8");
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            return writer.toString();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (TemplateException e) {
            log.error(e.getMessage(), e);
        }
        return templateContent;
    }

}
