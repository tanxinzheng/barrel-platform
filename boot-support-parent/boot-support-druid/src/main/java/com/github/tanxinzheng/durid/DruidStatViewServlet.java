package com.github.tanxinzheng.durid;

/**
 * Created by tanxinzheng on 2019/1/28.
 */

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 *
 * 注：需要在启动类中添加@ServletComponentScan注解方能访问页面
 * 1.需要重构用户权限认证
 */
@WebServlet(urlPatterns = "/druid/*", initParams={
        @WebInitParam(name="allow",value="localhost"),// IP白名单 (没有配置或者为空，则允许所有访问)
//        @WebInitParam(name="deny",value="192.168.16.111"),// IP黑名单 (存在共同时，deny优先于allow)
//        @WebInitParam(name="loginUsername",value="admin"),// 用户名
//        @WebInitParam(name="loginPassword",value="admin"),// 密码
        @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
})
public class DruidStatViewServlet extends StatViewServlet {
    private static final long serialVersionUID = 6588499385893299545L;
}
