package com.github.tanxinzheng.module.account.model;

import com.github.tanxinzheng.module.dictionary.web.DictionaryTransfer;
import com.github.tanxinzheng.web.json.DictionaryIndex;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by tanxinzheng on 16/9/2.
 */
@ApiModel(value = "账号")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountModel implements AccountDetail {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "电子邮箱")
    private String email;
    @ApiModelProperty(value = "手机号码")
    private String phone;
    @DictionaryTransfer(index = DictionaryIndex.ATTACHMENT_KEY, fieldName = "avatarUrl")
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "最后登录时间")
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
