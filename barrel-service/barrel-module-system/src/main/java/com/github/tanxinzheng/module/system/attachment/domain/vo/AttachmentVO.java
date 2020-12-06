package com.github.tanxinzheng.module.system.attachment.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "附件")
public class AttachmentVO implements Serializable {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "附件所属组")
    private String attachmentGroup;
    @ApiModelProperty(value = "附件KEY")
    private String attachmentKey;
    @ApiModelProperty(value = "附件大小")
    private Long attachmentSize;
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
    @ApiModelProperty(value = "关联ID")
    private String relationId;
    @ApiModelProperty(value = "属主：PUBLIC-公共可读，PRIVATE-私人可读，,<ROLE>-根据权限组可读")
    private String owner;
    @ApiModelProperty(value = "是否已删除")
    private Boolean isDelete;


}
