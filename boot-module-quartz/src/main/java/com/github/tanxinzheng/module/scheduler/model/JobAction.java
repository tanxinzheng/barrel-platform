package com.github.tanxinzheng.module.scheduler.model;

/**
 * Created by tanxinzheng on 2019/1/29.
 */
public enum JobAction {

    START("启动"),
    RUN("执行"),
    SHUTDOWN("暂停"),
    RESTART("启动"),
    STOP("停止"),
    DELETE("删除");


    private String actionDesc;

    JobAction(String actionDesc) {
        this.actionDesc = actionDesc;
    }
}
