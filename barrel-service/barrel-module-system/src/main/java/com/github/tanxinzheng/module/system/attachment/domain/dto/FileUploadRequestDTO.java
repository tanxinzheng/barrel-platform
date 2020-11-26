package com.github.tanxinzheng.module.system.attachment.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadRequestDTO {

    @NotNull(message = "请选择需要上传的文件")
    @ApiModelProperty(value = "文件流")
    private MultipartFile multipartFile;

    @NotNull(message = "关联类型为必填项")
    @ApiModelProperty(value = "关联类型")
    private String relationType;

    @NotNull(message = "属主为必填项")
    @ApiModelProperty(value = "属主：PUBLIC-公共可读，PRIVATE-私人可读，,<ROLE>-根据权限组可读")
    private String owner;

    @NotNull(message = "关联ID为必填项")
    @ApiModelProperty(value = "关联ID")
    private String relationId;
}
