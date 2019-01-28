package com.github.tanxinzheng.cloud.zuul.filter;

//import com.github.tanxinzheng.module.jwt.support.JwtUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tanxinzheng on 2019/1/5.
 */
@Slf4j
public class AccessFilter extends ZuulFilter {

    private static String access_token_header = "Authorization";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader("Authorization");
        if(StringUtils.isBlank(token)){
            log.warn("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("access token is empty");
            return "access token is empty";
        }
        ctx.addZuulRequestHeader("original_requestURL",request.getRequestURL().toString());
        ctx.addZuulRequestHeader(access_token_header, token);
//        ctx.addZuulRequestHeader("x-user-id", userInfo.getId());
//        ctx.addZuulRequestHeader("x-user-name",userInfo.getName());
        ctx.setSendZuulResponse(true); //对请求进行路由
        ctx.setResponseStatusCode(200);
        ctx.set("isSuccess", true);
        return null;
    }
}
