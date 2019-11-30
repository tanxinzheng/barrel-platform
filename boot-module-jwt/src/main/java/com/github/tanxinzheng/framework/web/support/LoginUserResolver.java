package com.github.tanxinzheng.framework.web.support;

import com.github.tanxinzheng.framework.web.annotation.LoginUser;
import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import com.github.tanxinzheng.jwt.support.JwtUser;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 解析@LoginUser注解参数，当前登录用户信息
 */
public class LoginUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(CurrentLoginUser.class) && methodParameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public CurrentLoginUser resolveArgument(MethodParameter methodParameter,
                                            ModelAndViewContainer modelAndViewContainer,
                                            NativeWebRequest nativeWebRequest,
                                            WebDataBinderFactory webDataBinderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            return null;
        }
        JwtUser jwtUser = (JwtUser) authentication.getDetails();
        CurrentLoginUser currentLoginUser = new CurrentLoginUser();
        currentLoginUser.setId(jwtUser.getId());
        currentLoginUser.setName(jwtUser.getName());
        currentLoginUser.setUsername(jwtUser.getUsername());
        currentLoginUser.setEmail(jwtUser.getEmail());
        return currentLoginUser;
    }
}
