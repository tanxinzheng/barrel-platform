package com.xmomen.module.jwt.support;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by tanxinzheng on 2018/6/15.
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    /**
     * token
     */
    private String token;

    /**
     * refreshToken
     */
    private String refreshToken;

    /**
     * 用户名
     */
    private String principal;
    /**
     * 认证凭证=token
     */
    private String credentials;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public JwtAuthenticationToken(String username, String token, String refreshToken, JwtUserDetails details,
                                  Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = username;
        this.token = token;
        this.refreshToken = refreshToken;
        this.credentials = token;
        setDetails(details);
        setAuthenticated(Boolean.TRUE);
    }

    /**
     * The credentials that prove the principal is correct. This is usually a password,
     * but could be anything relevant to the <code>AuthenticationManager</code>. Callers
     * are expected to populate the credentials.
     *
     * @return the credentials that prove the identity of the <code>Principal</code>
     */
    @Override
    public String getCredentials() {
        return this.credentials;
    }

    /**
     * The identity of the principal being authenticated. In the case of an authentication
     * request with username and password, this would be the username. Callers are
     * expected to populate the principal for an authentication request.
     * <p>
     * The <tt>AuthenticationManager</tt> implementation will often return an
     * <tt>Authentication</tt> containing richer information as the principal for use by
     * the application. Many of the authentication providers will create a
     * {@code UserDetails} object as the principal.
     *
     * @return the <code>Principal</code> being authenticated or the authenticated
     * principal after authentication.
     */
    @Override
    public String getPrincipal() {
        return this.principal;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
