package com.github.tanxinzheng.framework.web.support;

import com.github.tanxinzheng.framework.web.annotation.LoginUser;
import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(CurrentLoginUser.class) && methodParameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public CurrentLoginUser resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
//        return new Integer(1);
        CurrentLoginUser currentLoginUser = (CurrentLoginUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
        if(currentLoginUser != null){
            return currentLoginUser;
        }
        return null;
//        String token = nativeWebRequest.getHeader(LOGIN_TOKEN_KEY);
//        if (token == null || token.isEmpty()) {
//            return null;
//        }
//        return UserTokenManager.getUserId(token);
    }
}
