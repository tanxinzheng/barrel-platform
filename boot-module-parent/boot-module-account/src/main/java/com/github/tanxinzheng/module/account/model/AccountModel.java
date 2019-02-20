package com.github.tanxinzheng.module.account.model;

import com.github.tanxinzheng.framework.web.json.DictionaryIndex;
import com.github.tanxinzheng.module.dictionary.web.DictionaryTransfer;
import lombok.Data;

import java.util.Date;

/**
 * Created by tanxinzheng on 16/9/2.
 */
public @Data class AccountModel implements AccountDetail {

    private String id;
    private String name;
    private String email;
    private String phone;
    @DictionaryTransfer(index = DictionaryIndex.ATTACHMENT_KEY, fieldName = "avatarUrl")
    private String avatar;
    private Date lastLoginTime;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPhone() {
        return this.phone;
    }

}
