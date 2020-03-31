package com.github.tanxinzheng.module.notification.controller;

import com.github.tanxinzheng.framework.logger.ActionLog;
import com.github.tanxinzheng.framework.web.annotation.LoginUser;
import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import com.github.tanxinzheng.module.notification.model.*;
import com.github.tanxinzheng.module.notification.service.NotificationService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by tanxinzheng on 2018/11/19.
 */
@Api(tags = {"消息中心"})
@RestController
@RequestMapping("/account/notification")
public class AccountNotificationController {

    @Autowired
    NotificationService notificationService;


    /**
     * 用户当前消息
     * @return
     */
    @ApiOperation(value = "我的消息", notes = "默认查询三个月内消息")
    @GetMapping
    public List<AccountNotification> getAccountNotifications(@LoginUser CurrentLoginUser loginUser) {
        NotificationQuery notificationQuery = new NotificationQuery();
        notificationQuery.setUserId(loginUser.getId());
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
    public List<NotificationStateCount> notificationCount(
            @LoginUser CurrentLoginUser loginUser,
            NotificationQuery notificationQuery){
        if(notificationQuery == null){
            notificationQuery = new NotificationQuery();
        }
        notificationQuery.setUserId(loginUser.getId());
        return notificationService.countNotificationState(notificationQuery);
    }

    /**
     * 查询当前用户通知
     * @return
     */
    @RequestMapping(value = "/notification/count/unread", method = RequestMethod.GET)
    @ApiOperation(value = "查询当前用户资料信息")
    @ActionLog(actionName = "查询当前用户资料信息")
    public NotificationStateCount countUnRead(
            @LoginUser CurrentLoginUser loginUser,
            NotificationQuery notificationQuery){
        if(notificationQuery == null){
            notificationQuery = new NotificationQuery();
        }
        notificationQuery.setUserId(loginUser.getId());
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
