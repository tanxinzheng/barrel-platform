package com.github.tanxinzheng.module.notification.model;

import com.github.tanxinzheng.module.dictionary.web.DictionaryTransfer;
import com.github.tanxinzheng.web.json.DictionaryIndex;
import lombok.Data;

import java.util.List;

/**
 * Created by tanxinzheng on 2018/11/19.
 */
@Data
public class AccountNotification {

    private int count;
    @DictionaryTransfer(index = DictionaryIndex.NOTIFICATION_DATA_STATE)
    private String dataState;
    private List<NotificationModel> notificationModels;
}
