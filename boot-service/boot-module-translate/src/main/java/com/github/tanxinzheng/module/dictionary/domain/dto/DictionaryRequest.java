package com.github.tanxinzheng.module.dictionary.domain.dto;

import com.github.tanxinzheng.framework.model.QueryParams;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "数据字典请求参数")
public class DictionaryRequest extends QueryParams implements Serializable {
//
//    @ApiModelProperty(value = "主键")
//    private String id;
//    @ApiModelProperty(value = "主键集合")
//    private String[] ids;
//    @ApiModelProperty(value = "非主键集合")
//    private String[] excludeIds;
//    @ApiModelProperty(value = "字典代码")
//    private String code;
//    @ApiModelProperty(value = "字典类型")
//    private String type;
//    @ApiModelProperty(value = "父节点主键")
//    private String parentId;

}
