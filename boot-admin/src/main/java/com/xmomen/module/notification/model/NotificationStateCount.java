package com.xmomen.module.notification.model;

import com.github.tanxinzheng.module.dictionary.web.DictionaryTransfer;
import com.xmomen.framework.web.json.DictionaryIndex;
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
