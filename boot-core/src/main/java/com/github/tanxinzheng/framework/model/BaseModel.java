package com.github.tanxinzheng.framework.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jengt_000 on 2014/12/28.
 */
public class BaseModel implements Serializable {

    /** 创建人 */
    private String createdUserId;
    /** 创建时间 */
    private Date createdTime;
    /** 更新人 */
    private String updatedUserId;
    /** 更新时间 */
    private Date updatedTime;
    /** 数据版本号 */
    private Integer dataVersion;

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
    }
}