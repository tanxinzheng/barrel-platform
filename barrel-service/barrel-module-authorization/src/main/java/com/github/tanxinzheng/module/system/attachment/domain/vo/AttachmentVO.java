package com.github.tanxinzheng.module.system.attachment.domain.vo;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "附件")
public class AttachmentVO implements Serializable {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "附件所属组")
    private String attachmentGroup;
    @ApiModelProperty(value = "附件KEY")
    private String attachmentKey;
    @ApiModelProperty(value = "附件大小")
    private BigDecimal attachmentSize;
    @ApiModelProperty(value = "附件URL")
    private String attachmentPath;
    @ApiModelProperty(value = "附件后缀")
    private String attachmentSuffix;
    @ApiModelProperty(value = "原名称")
    private String originName;
    @ApiModelProperty(value = "上传时间")
    private LocalDateTime uploadTime;
    @ApiModelProperty(value = "上传人ID")
    private String uploadBy;
    @ApiModelProperty(value = "是否私有")
    private Boolean isPrivate;


}
