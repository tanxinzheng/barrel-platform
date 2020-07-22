package com.github.tanxinzheng.module.system.attachment.domain.dto;

import com.github.tanxinzheng.framework.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
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
@ApiModel(value = "附件")
public class AttachmentCreateDTO extends BaseModel implements Serializable {
    /** 附件所属组 */
    @NotBlank(message = "附件所属组为必填项")
    @Length(max = 20, message = "附件所属组字符长度限制[0,20]")
    @ApiModelProperty(value = "附件所属组")
    private String attachmentGroup;
    /** 是否私有 */
    @NotNull(message = "是否私有为必填项")
    @ApiModelProperty(value = "是否私有")
    private Boolean isPrivate;
    /** 文件信息 */
    @NotNull(message = "请选择需要上传的附件")
    @ApiModelProperty(value = "附件")
    private MultipartFile file;

}
