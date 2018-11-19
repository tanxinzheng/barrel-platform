package com.github.tanxinzheng.module.notification.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.tanxinzheng.test.TestAppController;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Assert;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tanxinzheng on 2018/11/19.
 */
public class AccountNotificationControllerTest extends TestAppController {

    @Test
    public void test0getAccountNotifications() throws Exception {
        ResultActions actions = mockMvc.perform(get("/account/notification")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.parseArray(resultJson);
        Assert.notEmpty(jsonArray, "查询消息列表，测试不通过");
        Assert.notEmpty(jsonArray, "查询消息列表，测试不通过");
    }

}