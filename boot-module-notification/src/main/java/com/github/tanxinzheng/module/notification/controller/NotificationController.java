package com.github.tanxinzheng.module.notification.controller;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.logger.ActionLog;
import com.github.tanxinzheng.framework.web.authentication.CurrentAccountService;
import com.github.tanxinzheng.module.notification.model.NotificationModel;
import com.github.tanxinzheng.module.notification.model.NotificationQuery;
import com.github.tanxinzheng.module.notification.model.SendNotification;
import com.github.tanxinzheng.module.notification.service.NotificationService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/notification")
public class NotificationController {

    private static Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    NotificationService notificationService;

    @Autowired
    CurrentAccountService currentAccountService;

    /**
     * 新增通知
     * @param   notificationModel  新增对象参数
     * @return  NotificationModel   通知领域对象
     */
    @ApiOperation(value = "新增通知")
    @ActionLog(actionName = "新增通知")
    @RequestMapping(method = RequestMethod.POST)
    public NotificationModel createNotification(@RequestBody @Valid SendNotification sendNotification) {
        return notificationService.send(sendNotification);
    }

    /**
     * 通知列表
     * @param   notificationQuery    通知查询参数对象
     * @return  Page<NotificationModel> 通知领域分页对象
     */
    @ApiOperation(value = "查询通知列表")
    @ActionLog(actionName = "查询通知列表")
    @RequestMapping(method = RequestMethod.GET)
    public Page<NotificationModel> getNotificationList(NotificationQuery notificationQuery){
        return notificationService.getNotificationModelPage(notificationQuery);
    }

    /**
     * 查询单个通知
     * @param   id  主键
     * @return  NotificationModel   通知领域对象
     */
    @ApiOperation(value = "查询通知")
    @ActionLog(actionName = "查询通知")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public NotificationModel getNotificationById(@PathVariable(value = "id") String id){
        return notificationService.getOneNotificationModel(id);
    }

    /**
     * 更新通知
     * @param id    主键
     * @param notificationModel  更新对象参数
     * @return  NotificationModel   通知领域对象
     */
    @ApiOperation(value = "更新通知")
    @ActionLog(actionName = "更新通知")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public NotificationModel updateNotification(@PathVariable(value = "id") String id,
                           @RequestBody @Valid NotificationModel notificationModel){
        if(StringUtils.isNotBlank(id)){
            notificationModel.setId(id);
        }
        notificationService.updateNotification(notificationModel);
        return notificationService.getOneNotificationModel(id);
    }

    /**
     * 已读消息
     * @param id
     */
    @ApiOperation(value = "更新通知")
    @RequestMapping(value = "/{id}/read", method = RequestMethod.PUT)
    public void readNotification(@PathVariable(value = "id") String id){
        notificationService.read(id, currentAccountService.getAccountId());
    }

    /**
     *  删除通知
     * @param id    主键
     */
    @ApiOperation(value = "删除单个通知")
    @ActionLog(actionName = "删除单个通知")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteNotification(@PathVariable(value = "id") String id){
        notificationService.deleteNotification(id);
    }

    /**
     *  删除通知
     * @param notificationQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除通知")
    @ActionLog(actionName = "批量删除通知")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteNotifications(NotificationQuery notificationQuery){
        if(notificationQuery == null || ArrayUtils.isEmpty(notificationQuery.getIds())){
            return;
        }
        notificationService.deleteNotification(notificationQuery.getIds());
    }


}
