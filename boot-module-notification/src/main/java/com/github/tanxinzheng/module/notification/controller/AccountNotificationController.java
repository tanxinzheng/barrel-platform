package com.github.tanxinzheng.module.notification.controller;

import com.github.tanxinzheng.framework.web.authentication.CurrentAccountService;
import com.github.tanxinzheng.module.notification.model.AccountNotification;
import com.github.tanxinzheng.module.notification.model.NotificationDataState;
import com.github.tanxinzheng.module.notification.model.NotificationModel;
import com.github.tanxinzheng.module.notification.model.NotificationQuery;
import com.github.tanxinzheng.module.notification.service.NotificationService;
import io.swagger.annotations.ApiOperation;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
