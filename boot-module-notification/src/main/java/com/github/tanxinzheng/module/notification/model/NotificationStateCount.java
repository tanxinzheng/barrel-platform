package com.github.tanxinzheng.module.notification.model;

import com.github.tanxinzheng.framework.web.json.DictionaryIndex;
import com.github.tanxinzheng.module.dictionary.web.DictionaryTransfer;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by tanxinzheng on 17/8/25.
 */
@Data
public class NotificationStateCount implements Serializable {

    @DictionaryTransfer(index = DictionaryIndex.NOTIFICATION_DATA_STATE, fieldName = "dataStateDesc")
    private String dataState;
    private int number;
}
