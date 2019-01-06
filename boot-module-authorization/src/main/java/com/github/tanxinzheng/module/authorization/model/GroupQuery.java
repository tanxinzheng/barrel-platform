package com.github.tanxinzheng.module.authorization.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.github.tanxinzheng.framework.model.BaseQuery;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 0:51:13
 * @version 1.0.0
 */

@ApiModel(value="用户组查询模型")
public @Data class GroupQuery extends BaseQuery implements Serializable {

    @ApiModelProperty(value="关键字")
    private String keyword;
    @ApiModelProperty(value="主键")
    private String id;
    private String[] ids;
    private String[] excludeIds;
    private String userId;
    private String groupType;
    private Boolean active;
    // true：已绑定用户组，false：未绑定用户组
    private Boolean hasBindGroup;

}
