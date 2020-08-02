package com.github.tanxinzheng.module.system.logger.aspect;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tanxinzheng.framework.logger.ActionLog;
import com.github.tanxinzheng.module.system.logger.domain.dto.ActionLogDTO;
import com.github.tanxinzheng.module.system.logger.service.ActionLogService;
import com.google.common.collect.Maps;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.*;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Jeng on 16/3/20.
 */
@Component
@Aspect
@Slf4j
public class ActionLoggerAspect {

    @Resource
    HttpServletRequest request;

    @Resource
    private ActionLogService actionLogService;

    @Resource
    ObjectMapper objectMapper;


    /**
     * 日志逻辑切入点
     */
    @Pointcut("@annotation(com.github.tanxinzheng.framework.logger.ActionLog)")
    public void getLogInfo() { }

    @Around(value = "getLogInfo()")
    public Object traceMethod(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnVal = null;
        String returnStr = null;
        String methodName = proceedingJoinPoint.getSignature().getName();
        final Object[] args = proceedingJoinPoint.getArgs();
        final String arguments;
        Object target = proceedingJoinPoint.getTarget();
        Method method = getMethodByClassAndName(target.getClass(), methodName);    //得到拦截的方法
        ActionLog an = (ActionLog)getAnnotationByMethod(method, ActionLog.class );
        if(an != null){
            methodName = an.actionName();
        }
        if (args == null || args.length == 0) {
            arguments = "";
        } else {
            arguments = Arrays.deepToString(args);
        }
        String username = (String) request.getAttribute("username");
        returnVal = proceedingJoinPoint.proceed();
        if(actionLogService != null){
            ActionLogDTO logModel = new ActionLogDTO();
            logModel.setActionDate(LocalDateTime.now());
            logModel.setTargetClass(target.getClass().getName());
            logModel.setTargetMethod(method.getName());
            logModel.setActionName(format(methodName, getAnnotationParamsByMethod(method, args)));
            logModel.setUserId(username);
            logModel.setClientIp(getRemoteHost(request));
            log.debug("User action record info -> {0}", JSONObject.toJSONString(logModel));
            actionLogService.createActionLog(logModel);
        }
        if (log.isDebugEnabled()) {
            log.debug("Entering method [{}] with arguments [{}]", methodName, arguments);
        }
        return returnVal;
    }


    public Annotation getAnnotationByMethod(Method method , Class annoClass){
        Annotation all[] = method.getAnnotations();
        for (Annotation annotation : all) {
            if (annotation.annotationType() == annoClass) {
                return annotation;
            }
        }
        return null;
    }

    public Map<String, Object> getAnnotationParamsByMethod(Method method , Object[] args){
        Map<String, Object> params = Maps.newHashMap();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] parameterAnnotation = parameterAnnotations[i];
            for (int j = 0; j < parameterAnnotation.length; j++) {
                Annotation annotation = parameterAnnotation[j];
                if(annotation instanceof RequestParam){
                    String val = ((RequestParam) annotation).value();
                    params.put(val, args[i]);
                }
                if(annotation instanceof RequestBody){
                    try {
                        String str = objectMapper.writeValueAsString(args[i]);
                        if(args[i].getClass().isArray()){
                            JSONArray data = JSONObject.parseArray(str);
                            params.put(args[i].getClass().getSimpleName(), data);
                        }else{
                            Map data = JSONObject.parseObject(str, Map.class);
                            params.put(args[i].getClass().getSimpleName(), data);
                        }
                    } catch (JsonProcessingException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }
        }
        return params;
    }

    public Method getMethodByClassAndName(Class c , String methodName){
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if(method.getName().equals(methodName)){
                return method ;
            }
        }
        return null;
    }

    /**
     * 获用户客户端IP
     * @param request
     * @return
     */
    public String getRemoteHost(HttpServletRequest request){
        if(request == null){
            return null;
        }
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }

    /**
     * 格式化描述信息
     * @param string
     * @param data
     * @return
     */
    public String format(String string, Map<String, Object> data){
        Configuration configuration = new Configuration(new Version(2,3,23));
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("ActionLogParamsTemplate", string);
        configuration.setTemplateLoader(stringTemplateLoader);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassicCompatible(true);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        try {
            Template template = configuration.getTemplate("ActionLogParamsTemplate", "UTF-8");
            StringWriter stringWriter = new StringWriter();
            template.process(data, stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (TemplateException e) {
            log.error(e.getMessage(), e);
        }
        return string;
    }

}
