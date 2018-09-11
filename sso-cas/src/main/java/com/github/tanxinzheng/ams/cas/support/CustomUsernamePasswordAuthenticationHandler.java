package com.github.tanxinzheng.ams.cas.support;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Map;

/**
 * Created by tanxinzheng on 2018/8/20.
 */
@Slf4j
public class CustomUsernamePasswordAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public CustomUsernamePasswordAuthenticationHandler(String name,
                                                       ServicesManager servicesManager,
                                                       PrincipalFactory principalFactory,
                                                       Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    /**
     * Performs the details of authentication and returns an authentication handler result on success.
     *
     * @param credential Credential to authenticate.
     * @return Authentication handler result on success.
     * @throws GeneralSecurityException On authentication failure that is thrown out to the caller of
     *                                  {@link #authenticate(Credential)}.
     * @throws PreventedException       On the indeterminate case when authentication is prevented.
     */
    @Override
    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {
        final UsernamePasswordCaptchaCredential originCredential = (UsernamePasswordCaptchaCredential) credential;
        UsernamePasswordCaptchaCredential userPassCredential = new UsernamePasswordCaptchaCredential(
                originCredential.getUsername(),
                originCredential.getPassword(),
                originCredential.isRememberMe()
        );
        if (StringUtils.isBlank(originCredential.getUsername())) {
            throw new AccountNotFoundException("Username is null.");
        }
        if (StringUtils.isBlank(originCredential.getPassword())) {
            throw new FailedLoginException("Password is null.");
        }
        Map<String, Object> values = jdbcTemplate.queryForMap("select * from cas_user where username = ?", originCredential.getUsername());
        String salt = (String) values.get("salt");
        String md5Password = (String) values.get("password");
        final String transformedPsw = encodePassword(originCredential.getPassword(), salt);
        if (StringUtils.isBlank(transformedPsw)) {
            throw new AccountNotFoundException("Encoded password is null.");
        }
        if(!md5Password.equals(transformedPsw)){
            throw new FailedLoginException("Password does not match value on record.");
        }
        userPassCredential.setPassword(transformedPsw);
        log.debug("Attempting authentication internally for transformed credential [{}]", userPassCredential);
        String email = (String) values.get("email");
        String phone = (String) values.get("phone");
        Map<String, Object> attributes = Maps.newHashMap();
        attributes.put("email", email);
        attributes.put("phone", phone);
        return createHandlerResult(userPassCredential, this.principalFactory.createPrincipal(userPassCredential.getUsername(), attributes));
    }


    private String encodePassword(String originPassword, String salt){
        log.debug("encrypt password: {}, salt: {}", originPassword, salt);
        String password = originPassword + salt;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    @Override
    public boolean supports(Credential credential) {
        return credential instanceof UsernamePasswordCaptchaCredential;
    }
}
