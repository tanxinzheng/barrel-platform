package com.github.tanxinzheng.module.dictionary.model;

import com.github.tanxinzheng.framework.model.BaseQuery;
import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-6-11 1:07:45
 * @version 1.0.0
 */
@Data
public class DictionaryQuery extends BaseQuery implements Serializable {

    private String keyword;
    private String parentId;
    private String id;
    private String[] ids;
    private String[] excludeIds;
    private String code;
    private String type;

}
