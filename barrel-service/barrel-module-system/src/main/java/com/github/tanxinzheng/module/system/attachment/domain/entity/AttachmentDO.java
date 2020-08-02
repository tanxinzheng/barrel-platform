package com.github.tanxinzheng.module.system.attachment.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.tanxinzheng.framework.model.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-6 16:47:03
 */
@Data
@TableName(value = "xmo_attachment")
public class AttachmentDO extends BaseEntity implements Serializable {

    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /** 附件所属组 */
    @TableField(value = "ATTACHMENT_GROUP")
    private String attachmentGroup;
    /** 附件KEY */
    @TableField(value = "ATTACHMENT_KEY")
    private String attachmentKey;
    /** 附件大小 */
    @TableField(value = "ATTACHMENT_SIZE")
    private Long attachmentSize;
    /** 附件URL */
    @TableField(value = "ATTACHMENT_PATH")
    private String attachmentPath;
    /** 附件后缀 */
    @TableField(value = "ATTACHMENT_SUFFIX")
    private String attachmentSuffix;
    /** 原名称 */
    @TableField(value = "ORIGIN_NAME")
    private String originName;
    /** 上传时间 */
    @TableField(value = "UPLOAD_TIME")
    private LocalDateTime uploadTime;
    /** 上传人ID */
    @TableField(value = "UPLOAD_BY")
    private String uploadBy;
    /** 关联ID */
    @TableField(value = "RELATION_ID")
    private String relationId;
    /** 属主 */
    @TableField(value = "OWNER")
    private String owner;
    /** 是否已删除:  Y-已删除，N-未删除 */
    @TableField(value = "IS_DELETE")
    private Boolean isDelete;


}
