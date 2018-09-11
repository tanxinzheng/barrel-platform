package com.github.tanxinzheng.ams.cas.support;



import org.apereo.cas.authentication.RememberMeUsernamePasswordCredential;

import javax.validation.constraints.Size;

/**
 * Created by tanxinzheng on 2018/8/19.
 */
public class UsernamePasswordCaptchaCredential extends RememberMeUsernamePasswordCredential {

    public UsernamePasswordCaptchaCredential() {
    }

    public UsernamePasswordCaptchaCredential(String username, String password, boolean rememberMe) {
        setUsername(username);
        setPassword(password);
        setRememberMe(rememberMe);
    }

    @Size(min = 5, max = 5, message = "require captcha")
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
