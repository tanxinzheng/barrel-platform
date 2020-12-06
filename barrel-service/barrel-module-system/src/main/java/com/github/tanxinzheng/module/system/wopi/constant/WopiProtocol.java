package com.github.tanxinzheng.module.system.wopi.constant;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Date 2020/12/6
 */
public enum WopiProtocol {

    PDF("http://{}/x/_layouts/xlviewerinternal.aspx?WOPISrc={}"),
    WORD("http://{}/x/_layouts/xlviewerinternal.aspx?WOPISrc={}"),
    PPT("http://{}/x/_layouts/xlviewerinternal.aspx?WOPISrc={}"),
    EXCEL("http://{}/x/_layouts/xlviewerinternal.aspx?WOPISrc={}"),
    GIF("http://{}/x/_layouts/xlviewerinternal.aspx?WOPISrc={}")
    ;

    WopiProtocol(String officeOnlineServerProtocol) {
        this.officeOnlineServerProtocol = officeOnlineServerProtocol;
    }

    private String officeOnlineServerProtocol;

    public String getOfficeOnlineServerProtocol() {
        return officeOnlineServerProtocol;
    }

    public void setOfficeOnlineServerProtocol(String officeOnlineServerProtocol) {
        this.officeOnlineServerProtocol = officeOnlineServerProtocol;
    }

}
