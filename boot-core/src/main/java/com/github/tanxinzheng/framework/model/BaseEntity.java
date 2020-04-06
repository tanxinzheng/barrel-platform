package com.github.tanxinzheng.framework.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tanxinzheng on 17/6/7.
 */
@Data
public class BaseEntity implements Serializable {

    @TableField(value = "CREATED_USER_ID", fill = FieldFill.INSERT)
    private String createdUserId;
    @TableField(value = "CREATED_TIME", fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(value = "UPDATED_USER_ID", fill = FieldFill.INSERT_UPDATE)
    private String updatedUserId;
    @TableField(value = "UPDATED_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

}
