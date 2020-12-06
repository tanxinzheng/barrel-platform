package com.github.tanxinzheng.module.system.wopi.constant;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Date 2020/12/6
 */
public enum WopiProtocol {

    PDF("{0}/wv/wordviewerframe.aspx?WOPISrc={1}/wopi/files/{2}", Boolean.TRUE, new String[]{"pdf"}),
    WORD("{0}/wv/wordviewerframe.aspx?WOPISrc={1}/wopi/files/{2}", Boolean.TRUE, new String[]{"doc", "docx"}),
    PPT("{0}/p/PowerPointFrame.aspx?WOPISrc={1}/wopi/files/{2}", Boolean.TRUE, new String[]{"ppt", "pptx"}),
    EXCEL("{0}/x/_layouts/xlviewerinternal.aspx?WOPISrc={1}/wopi/files/{2}", Boolean.TRUE, new String[]{"xls", "xlsx"}),
    IMAGE("{0}/preview/image/{1}", Boolean.FALSE, new String[]{"bmp", "jpg", "png", "gif"})
    ;

    private String protocol;
    private boolean wopiProtocol;
    private String[] fileType;

    WopiProtocol(String protocol, boolean wopiProtocol, String[] fileType) {
        this.protocol = protocol;
        this.fileType = fileType;
        this.wopiProtocol = wopiProtocol;
    }

    public boolean isWopiProtocol() {
        return wopiProtocol;
    }

    public void setWopiProtocol(boolean wopiProtocol) {
        this.wopiProtocol = wopiProtocol;
    }

    public String[] getFileType() {
        return fileType;
    }

    public void setFileType(String[] fileType) {
        this.fileType = fileType;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String toPreviewUrl(String officeOnlineServer, String wopiServer, String fileKey){
        if(!(officeOnlineServer.startsWith("http://") || officeOnlineServer.startsWith("https://"))){
            officeOnlineServer = "http://" + officeOnlineServer;
        }
        if(!(wopiServer.startsWith("http://") || wopiServer.startsWith("https://"))){
            wopiServer = "http://" + wopiServer;
        }
        return MessageFormat.format(this.protocol, officeOnlineServer, wopiServer, fileKey);
    }

    public static WopiProtocol toProtocol(String fileSuffix){
        for (WopiProtocol wopiProtocol : WopiProtocol.values()) {
            String[] suffix = wopiProtocol.getFileType();
            if(StringUtils.isNotBlank(fileSuffix) && ArrayUtils.contains(suffix, fileSuffix.toLowerCase())){
                return wopiProtocol;
            }
        }
        return null;
    }
}
