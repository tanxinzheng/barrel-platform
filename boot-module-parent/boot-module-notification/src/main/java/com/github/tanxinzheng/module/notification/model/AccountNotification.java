package com.github.tanxinzheng.module.notification.model;

import com.github.tanxinzheng.framework.web.json.DictionaryIndex;
import com.github.tanxinzheng.module.dictionary.web.DictionaryTransfer;
import lombok.Data;

import java.util.List;

/**
 * Created by tanxinzheng on 2018/11/19.
 */
@Data
public class AccountNotification {

    private int count;
    @DictionaryTransfer(index = DictionaryIndex.NOTIFICATION_DATA_STATE, fieldName = "dataStateDesc")
    private String dataState;
    private List<NotificationModel> notificationModels;
}
