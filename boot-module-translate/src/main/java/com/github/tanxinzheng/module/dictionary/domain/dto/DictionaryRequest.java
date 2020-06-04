package com.github.tanxinzheng.module.dictionary.domain.dto;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.tanxinzheng.framework.model.BaseQuery;
import com.github.tanxinzheng.framework.model.QueryWrapperCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DictionaryRequest extends BaseQuery implements Serializable {

    private String id;
    private String[] ids;
    private String[] excludeIds;
    private String code;
    private String type;
    private String parentId;

}
