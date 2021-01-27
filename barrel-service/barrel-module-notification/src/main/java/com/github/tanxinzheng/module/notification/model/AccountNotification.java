package com.github.tanxinzheng.module.notification.model;

import com.github.tanxinzheng.framework.web.annotation.DictionaryTransfer;
import com.github.tanxinzheng.framework.web.json.DictionaryIndex;
import lombok.Data;

import java.util.List;

/**
 * Created by tanxinzheng on 2018/11/19.
 */
@Data
public class AccountNotification {

    private int count;
    @DictionaryTransfer(type = DictionaryIndex.NOTIFICATION_DATA_STATE)
    private String dataState;
    private List<NotificationModel> notificationModels;
}
