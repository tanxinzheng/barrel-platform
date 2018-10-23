package com.xmomen.module.jwt.support.access;

import com.google.common.collect.Lists;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tanxinzheng on 2018/10/21.
 */
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private SecurityMetadataHandler securityMetadataHandler;

    public MyFilterInvocationSecurityMetadataSource(SecurityMetadataHandler securityMetadataHandler) {
        this.securityMetadataHandler = securityMetadataHandler;
    }

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private List<PermissionGrantedAuthority> authorityList = Lists.newArrayList();
    /**
     * Accesses the {@code ConfigAttribute}s that apply to a given secure object.
     *
     * @param object the object being secured
     * @return the attributes that apply to the passed in secured object. Should return an
     * empty collection if there are no applicable attributes.
     * @throws IllegalArgumentException if the passed object is not of a type supported by
     *                                  the <code>SecurityMetadataSource</code> implementation
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        String requestUrl = fi.getRequestUrl();
        String requestMethod = fi.getRequest().getMethod();
        authorityList = securityMetadataHandler.loadAllPermission();
        for (PermissionGrantedAuthority permissionGrantedAuthority : authorityList) {
            if(antPathMatcher.match(permissionGrantedAuthority.getUrl(), requestUrl)
                    && permissionGrantedAuthority.getRequestMethod().equals(requestMethod)){
                return SecurityConfig.createList(permissionGrantedAuthority.getRoles()
                        .toArray(new String[permissionGrantedAuthority.getRoles().size()]));
            }
        }
        //没有匹配到,默认是要登录才能访问
        return SecurityConfig.createList("ROLE_USER");
    }

    /**
     * If available, returns all of the {@code ConfigAttribute}s defined by the
     * implementing class.
     * <p>
     * This is used by the {@link AbstractSecurityInterceptor} to perform startup time
     * validation of each {@code ConfigAttribute} configured against it.
     *
     * @return the {@code ConfigAttribute}s or {@code null} if unsupported
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * Indicates whether the {@code SecurityMetadataSource} implementation is able to
     * provide {@code ConfigAttribute}s for the indicated secure object type.
     *
     * @param clazz the class that is being queried
     * @return true if the implementation can process the indicated class
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
