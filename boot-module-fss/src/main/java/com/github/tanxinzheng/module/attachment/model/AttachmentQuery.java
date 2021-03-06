package com.github.tanxinzheng.module.attachment.model;

import com.github.tanxinzheng.framework.model.BaseQuery;
import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-8-6 15:56:07
 * @version 1.0.0
 */
@Data
public class AttachmentQuery extends BaseQuery implements Serializable {

    private String keyword;
    private String attachmentKey;
    private String[] attachmentKeys;
    private String id;
    private String[] ids;
    private String[] excludeIds;

}
