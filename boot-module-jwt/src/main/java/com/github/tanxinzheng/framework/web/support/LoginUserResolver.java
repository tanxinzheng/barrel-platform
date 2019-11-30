package com.github.tanxinzheng.framework.web.support;

import com.github.tanxinzheng.framework.web.annotation.LoginUser;
import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 解析@LoginUser注解参数，当前登录用户信息
 */
@Component
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
        CurrentLoginUser currentLoginUser = (CurrentLoginUser) authentication.getDetails();
        return currentLoginUser;
    }
}
