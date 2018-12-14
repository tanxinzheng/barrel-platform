package com.github.tanxinzheng.module.logger.model;

import com.github.tanxinzheng.framework.model.BaseQuery;
import lombok.Data;

import java.util.Date;

/**
 * Created by tanxinzheng on 17/8/5.
 */
@Data
public class ActionLogQuery extends BaseQuery {

    private String keyword;
    private String username;
    private String userId;
    private Date startActionDate;
    private Date endActionDate;
}
