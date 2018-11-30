package com.github.tanxinzheng.module.notification.controller;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.logger.ActionLog;
import com.github.tanxinzheng.framework.web.authentication.CurrentAccountService;
import com.github.tanxinzheng.module.notification.model.*;
import com.github.tanxinzheng.module.notification.service.NotificationService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by tanxinzheng on 2018/11/19.
 */
@RestController
@RequestMapping("/account/notification")
public class AccountNotificationController {

    @Autowired
    CurrentAccountService currentAccountService;

    @Autowired
    NotificationService notificationService;


    /**
     * 用户当前消息
     * @return
     */
    @ApiOperation(value = "查询当前用户消息，默认查询三个月内")
    @RequestMapping(method = RequestMethod.GET)
    public List<AccountNotification> getAccountNotifications() {
        NotificationQuery notificationQuery = new NotificationQuery();
        notificationQuery.setUserId(currentAccountService.getAccountId());
        List<NotificationModel> list = notificationService.getNotificationModelList(notificationQuery);
        List<AccountNotification> result = Lists.newArrayList();
        for (NotificationDataState notificationDataState : NotificationDataState.values()) {
            AccountNotification accountNotification = new AccountNotification();
            accountNotification.setDataState(notificationDataState.name());
            List<NotificationModel> notificationModelList = Lists.newArrayList();
            for (NotificationModel model : list) {
                if (notificationDataState.name().equalsIgnoreCase(model.getDataState())){
                    notificationModelList.add(model);
                }
            }
            accountNotification.setNotificationModels(notificationModelList);
            accountNotification.setCount(notificationModelList.size());
            result.add(accountNotification);
        }
        return result;
    }

//    /**
//     * 查询当前用户通知
//     * @return
//     */
//    @RequestMapping(value = "/notification", method = RequestMethod.GET)
//    @ApiOperation(value = "查询当前用户资料信息")
//    @ActionLog(actionName = "查询当前用户资料信息")
//    public Page<NotificationModel> getNotificationPage(NotificationQuery notificationQuery){
//        if(notificationQuery == null){
//            notificationQuery = new NotificationQuery();
//        }
//        notificationQuery.setUserId(currentAccountService.getAccountId());
//        return notificationReceiveService.selectNotification(notificationQuery);
//    }
//
//    /**
//     * 查询当前用户通知
//     * @return
//     */
//    @RequestMapping(value = "/notification/{id}", method = RequestMethod.GET)
//    @ApiOperation(value = "查询当前用户资料信息")
//    @ActionLog(actionName = "查询当前用户资料信息")
//    public NotificationModel getNotification(@PathVariable(value = "id") String id){
//        return notificationReceiveService.selectOneNotificationModel(id);
//    }

    /**
     * 查询当前用户通知
     * @return
     */
    @RequestMapping(value = "/notification/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户资料信息")
    @ActionLog(actionName = "查询当前用户资料信息")
    public List<NotificationStateCount> notificationCount(NotificationQuery notificationQuery){
        if(notificationQuery == null){
            notificationQuery = new NotificationQuery();
        }
        notificationQuery.setUserId(currentAccountService.getAccountId());
        return notificationService.countNotificationState(notificationQuery);
    }

    /**
     * 查询当前用户通知
     * @return
     */
    @RequestMapping(value = "/notification/count/unread", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户资料信息")
    @ActionLog(actionName = "查询当前用户资料信息")
    public NotificationStateCount countUnRead(NotificationQuery notificationQuery){
        if(notificationQuery == null){
            notificationQuery = new NotificationQuery();
        }
        notificationQuery.setUserId(currentAccountService.getAccountId());
        List<NotificationStateCount> list = notificationService.countNotificationState(notificationQuery);
        if(CollectionUtils.isEmpty(list)){
            return new NotificationStateCount();
        }
        Optional<NotificationStateCount> stateCount = list.stream().filter(notificationStateCount -> {
            return NotificationDataState.UNREAD.name().equals(notificationStateCount.getDataState());
        }).findFirst();
        if(stateCount.isPresent()){
            return stateCount.get();
        }
        return new NotificationStateCount();
    }
}
