package com.github.tanxinzheng.module.system.wopi.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 * @Description:
 *      office online
 * @Author:
 *      tanxinzheng
 * @Date:
 *      2020/12/6
 */
@Component
@ConfigurationProperties(prefix = "barrel.office-online")
public class OfficeOnlineProperties {

    private String previewServer;
    private String wopiServer;

    public String getPreviewServer() {
        return previewServer;
    }

    public void setPreviewServer(String previewServer) {
        this.previewServer = previewServer;
    }

    public String getWopiServer() {
        return wopiServer;
    }

    public void setWopiServer(String wopiServer) {
        this.wopiServer = wopiServer;
    }
}
