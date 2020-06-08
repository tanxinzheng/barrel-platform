package com.github.tanxinzheng.module.attachment.model;

import com.github.tanxinzheng.framework.model.BaseEntity;
import com.github.tanxinzheng.module.dictionary.web.AccountField;
import com.github.tanxinzheng.module.dictionary.web.DictionaryTransfer;
import com.github.tanxinzheng.web.json.DictionaryIndex;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author  tanxinzheng
 * @date    2017-8-6 15:56:07
 * @version 1.0.0
 */
@Data
public class Attachment extends BaseEntity implements Serializable {

    /** 主键 */
    private String id;
    /** 附件所属组 */
    private String attachmentGroup;
    /** 附件KEY */
    @DictionaryTransfer(index = DictionaryIndex.ATTACHMENT_KEY, fieldName = "attachmentUrl")
    private String attachmentKey;
    /** 附件大小 */
    private Long attachmentSize;
    /** 附件URL */
    private String attachmentPath;
    /** 附件后缀 */
    private String attachmentSuffix;
    /** 原名称 */
    private String originName;
    /** 上传时间 */
    private Date uploadTime;
    /** 上传人ID */
    @AccountField
    private String uploadUserId;
    /** 关联ID */
    private String relationId;
    /** 是否私有 */
    private Boolean isPrivate;


}
