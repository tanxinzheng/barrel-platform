package com.github.tanxinzheng.framework.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tanxinzheng on 2018/10/22.
 */
@Data
public class TreeModel<T> implements Serializable {

    private String id;
    private String parentId;
    private String label;
    private T value;
    private List<TreeModel> children;


}
