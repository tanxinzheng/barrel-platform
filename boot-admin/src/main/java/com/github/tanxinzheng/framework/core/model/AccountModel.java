package com.github.tanxinzheng.framework.core.model;

import com.github.tanxinzheng.framework.web.json.DictionaryIndex;
import com.github.tanxinzheng.module.dictionary.web.DictionaryTransfer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by tanxinzheng on 16/9/2.
 */
public @Data class AccountModel implements Serializable {

    private String userId;
    private String username;
    private String nickname;
    private String email;
    private String phoneNumber;
    private Date createdTime;
    private Date lastLoginTime;
    private Boolean locked;
    @DictionaryTransfer(index = DictionaryIndex.ATTACHMENT_KEY, fieldName = "avatarUrl")
    private String avatar;
    private List<NavItem> navItems;

    @Data
    public static class NavItem implements Serializable {
        private String url;
        private String name;
        private String title;
    }

}
