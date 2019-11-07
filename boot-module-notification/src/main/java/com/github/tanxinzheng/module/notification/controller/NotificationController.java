package com.github.tanxinzheng.module.notification.controller;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.web.annotation.LoginUser;
import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import com.github.tanxinzheng.module.notification.model.NotificationModel;
import com.github.tanxinzheng.module.notification.model.NotificationQuery;
import com.github.tanxinzheng.module.notification.model.SendNotification;
import com.github.tanxinzheng.module.notification.service.NotificationService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    NotificationService notificationService;

    /**
     * 新增通知
     * @param   sendNotification  新增对象参数
     * @return  NotificationModel   通知领域对象
     */
    @ApiOperation(value = "新增通知")
    @PostMapping
    public NotificationModel createNotification(@RequestBody @Valid SendNotification sendNotification) {
        return notificationService.send(sendNotification);
    }

    /**
     * 通知列表
     * @param   notificationQuery    通知查询参数对象
     * @return  Page<NotificationModel> 通知领域分页对象
     */
    @ApiOperation(value = "查询通知列表")
    @GetMapping
    public Page<NotificationModel> getNotificationList(NotificationQuery notificationQuery){
        return notificationService.getNotificationModelPage(notificationQuery);
    }

    /**
     * 查询单个通知
     * @param   id  主键
     * @return  NotificationModel   通知领域对象
     */
    @ApiOperation(value = "查询通知")
    @GetMapping(value = "/{id}")
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
    @PutMapping(value = "/{id}")
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
    public void readNotification(@LoginUser CurrentLoginUser loginUser,
                                 @PathVariable(value = "id") String id){
        notificationService.read(id, loginUser.getId());
    }

    /**
     *  删除通知
     * @param id    主键
     */
    @ApiOperation(value = "删除单个通知")
    @DeleteMapping(value = "/{id}")
    public void deleteNotification(@PathVariable(value = "id") String id){
        notificationService.deleteNotification(id);
    }

    /**
     *  删除通知
     * @param notificationQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除通知")
    @DeleteMapping
    public void deleteNotifications(NotificationQuery notificationQuery){
        if(notificationQuery == null || ArrayUtils.isEmpty(notificationQuery.getIds())){
            return;
        }
        notificationService.deleteNotification(notificationQuery.getIds());
    }


}
