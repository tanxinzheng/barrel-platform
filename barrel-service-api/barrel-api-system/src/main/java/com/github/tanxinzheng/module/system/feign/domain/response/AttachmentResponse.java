package com.github.tanxinzheng.module.system.feign.domain.response;

import com.github.tanxinzheng.framework.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-6 16:47:03
 */
@Data
@ApiModel(value = "附件")
public class AttachmentResponse extends BaseModel implements Serializable {
    /** 主键 */
    @Length(max = 32, message = "主键字符长度限制[0,32]")
    @ApiModelProperty(value = "主键")
    private String id;
    /** 附件所属组 */
    @NotBlank(message = "附件所属组为必填项")
    @Length(max = 20, message = "附件所属组字符长度限制[0,20]")
    @ApiModelProperty(value = "附件所属组")
    private String attachmentGroup;
    /** 附件KEY */
    @NotBlank(message = "附件KEY为必填项")
    @Length(max = 50, message = "附件KEY字符长度限制[0,50]")
    @ApiModelProperty(value = "附件KEY")
    private String attachmentKey;
    /** 附件大小 */
    @NotNull(message = "附件大小为必填项")
    @ApiModelProperty(value = "附件大小")
    private Long attachmentSize;
    /** 附件URL */
    @NotBlank(message = "附件URL为必填项")
    @Length(max = 200, message = "附件URL字符长度限制[0,200]")
    @ApiModelProperty(value = "附件URL")
    private String attachmentPath;
    /** 附件后缀 */
    @NotBlank(message = "附件后缀为必填项")
    @Length(max = 100, message = "附件后缀字符长度限制[0,100]")
    @ApiModelProperty(value = "附件后缀")
    private String attachmentSuffix;
    /** 原名称 */
    @NotBlank(message = "原名称为必填项")
    @Length(max = 100, message = "原名称字符长度限制[0,100]")
    @ApiModelProperty(value = "原名称")
    private String originName;
    /** 上传时间 */
    @NotNull(message = "上传时间为必填项")
    @ApiModelProperty(value = "上传时间")
    private LocalDateTime uploadTime;
    /** 上传人ID */
    @NotBlank(message = "上传人ID为必填项")
    @Length(max = 32, message = "上传人ID字符长度限制[0,32]")
    @ApiModelProperty(value = "上传人ID")
    private String uploadBy;
    /** 是否私有 */
    @NotNull(message = "是否私有为必填项")
    @ApiModelProperty(value = "是否私有")
    private Boolean isPrivate;
    /** 文件信息 */
    @NotNull(message = "请选择需要上传的附件")
    private MultipartFile multipartFile;

}
