package com.github.tanxinzheng.module.system.authorization.constant;

public enum PermissionType {

    BUTTON("按钮"),
    MENU("菜单"),
    URL("接口");

    private String decs;

    public String getDecs() {
        return decs;
    }

    public void setDecs(String decs) {
        this.decs = decs;
    }

    PermissionType(String decs) {
        this.decs = decs;
    }
}
