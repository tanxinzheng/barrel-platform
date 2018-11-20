package com.github.tanxinzheng.module.notification.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.tanxinzheng.module.notification.model.NotificationTemplateModel;
import com.github.tanxinzheng.module.notification.model.NotificationType;
import com.github.tanxinzheng.module.notification.model.SendNotification;
import com.github.tanxinzheng.test.TestAppController;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tanxinzheng on 2018/11/18.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotificationControllerTest extends TestAppController {

    private String TEMPLATE_CODE = "USER_LOGIN_SUCCESS";

    @Test
    public void test0createNotificationTemplate() throws Exception {
        NotificationTemplateModel templateModel = new NotificationTemplateModel();
        templateModel.setTemplateCode(TEMPLATE_CODE);
        templateModel.setTemplateName("用户登录成功消息通知");
        templateModel.setTemplateTitle("${USER_NAME}登录成功");
        templateModel.setTemplateBody("${USER_NAME}于${LOGIN_TIME}成功登录系统。");
        ResultActions actions = mockMvc.perform(post("/notification/template")
                .content(JSONObject.toJSONString(templateModel))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONObject resultObj = JSONObject.parseObject(resultJson);
        Assert.notEmpty(resultObj, "新增消息模板，测试不通过");
        Assert.notNull(resultObj.get("id"), "新增消息模板，测试不通过");
    }

    @Test
    public void test1createNotification() throws Exception {
        Map<String, Object> data = Maps.newHashMap();
        data.put("USER_NAME", "张三");
        data.put("LOGIN_TIME", DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
        SendNotification sendNotification = new SendNotification();
        sendNotification.setTemplateCode(TEMPLATE_CODE);
        sendNotification.setNotificationType(NotificationType.WEB.name());
        sendNotification.setReceiver(new String[]{"TEST_USER"});
        sendNotification.setTemplateData(data);
        ResultActions actions = mockMvc.perform(post("/notification")
                .content(JSONObject.toJSONString(sendNotification))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONObject resultObj = JSONObject.parseObject(resultJson);
        Assert.notEmpty(resultObj, "新增消息，测试不通过");
        Assert.notNull(resultObj.get("id"), "新增消息，测试不通过");
    }

    @Test
    public void test2getNotificationList() throws Exception {
        ResultActions actions = mockMvc.perform(get("/notification")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.parseArray(resultJson);
        Assert.notEmpty(jsonArray, "查询消息列表，测试不通过");
    }

    @Test
    public void test3getNotificationById() throws Exception {

    }

    @Test
    public void test4updateNotification() throws Exception {
    }

    @Test
    public void test5deleteNotification() throws Exception {
    }

    @Test
    public void test6deleteNotifications() throws Exception {
    }

}