package com.github.tanxinzheng.module.system.logger.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.tanxinzheng.framework.model.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-7 10:42:23
 */
@Data
@TableName(value = "xmo_action_log")
public class ActionLogDO extends BaseEntity implements Serializable {

    /** 物理主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /** 用户ID */
    @TableField(value = "USER_ID")
    private String userId;
    /** 操作名称 */
    @TableField(value = "ACTION_NAME")
    private String actionName;
    /** 操作时间 */
    @TableField(value = "ACTION_DATE")
    private LocalDateTime actionDate;
    /** 参数 */
    @TableField(value = "ACTION_PARAMS")
    private String actionParams;
    /** 客户端IP */
    @TableField(value = "CLIENT_IP")
    private String clientIp;
    /** 操作结果 */
    @TableField(value = "ACTION_RESULT")
    private String actionResult;
    /** 类名 */
    @TableField(value = "TARGET_CLASS")
    private String targetClass;
    /** 方法名 */
    @TableField(value = "TARGET_METHOD")
    private String targetMethod;


}
