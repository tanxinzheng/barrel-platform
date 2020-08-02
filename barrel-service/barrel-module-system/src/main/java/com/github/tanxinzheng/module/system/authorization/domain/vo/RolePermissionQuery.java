package com.github.tanxinzheng.module.system.authorization.domain.vo;

import com.github.tanxinzheng.framework.mybatis.domian.QueryParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Date 2020/6/26
 */
@ApiModel(description = "查询对象")
@Data
public class RolePermissionQuery extends QueryParams {

    @ApiModelProperty(value = "角色主键")
    private String roleId;
    @ApiModelProperty(value = "查询已/未绑定：true-绑定，false-未绑定")
    private Boolean isRelate;
}
