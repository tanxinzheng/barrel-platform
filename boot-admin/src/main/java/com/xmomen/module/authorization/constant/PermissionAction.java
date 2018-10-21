package com.xmomen.module.authorization.constant;

/**
 * Created by tanxinzheng on 17/8/1.
 */
public enum PermissionAction {
    VIEW("查看"),
    EDIT("编辑"),
    ADD("新增"),
    REMOVE("删除"),
    FILTER("过滤"),
    IMPORT("导入"),
    EXPORT("导出")
    ;

    PermissionAction(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
