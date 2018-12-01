package com.github.tanxinzheng.module.authorization.model;

import com.github.tanxinzheng.module.dictionary.web.DictionaryTransfer;
import com.github.tanxinzheng.framework.model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.tanxinzheng.framework.web.json.DictionaryIndex;
import lombok.Data;
import org.hibernate.validator.constraints.*;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@ExcelTarget(value = "UserGroupModel")
public @Data class UserGroupModel extends BaseModel implements Serializable {

    /**  */
    @Length(max = 32, message = "字符长度限制[0,32]")
    private String id;
    /** 用户表ID */
    @Excel(name = "用户表ID")
    @NotBlank(message = "用户表ID为必填项")
    @Length(max = 32, message = "用户表ID字符长度限制[0,32]")
    private String userId;
    @DictionaryTransfer(index = DictionaryIndex.ATTACHMENT_KEY, fieldName = "avatarUrl")
    private String avatar;
    private String username;
    private String nickname;
    /** 组表ID */
    @Excel(name = "组表ID")
    @NotBlank(message = "组表ID为必填项")
    @Length(max = 32, message = "组表ID字符长度限制[0,32]")
    private String groupId;

    private String groupName;

    /**
    * Get UserGroup Entity Object
    * @return
    */
    @JsonIgnore
    public UserGroup getEntity(){
        UserGroup userGroup = new UserGroup();
        BeanUtils.copyProperties(this, userGroup);
        return userGroup;
    }


}
