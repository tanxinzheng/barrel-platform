package com.github.tanxinzheng.module.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.tanxinzheng.framework.model.Result;
import com.github.tanxinzheng.module.auth.AuthApplication;
import com.github.tanxinzheng.module.auth.domain.dto.LoginRequest;
import com.github.tanxinzheng.module.auth.domain.dto.LoginResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Date 2020/6/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AuthApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthControllerTest {
    /**
     * Interface to provide configuration for a web application.
     */
    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    /**
     * 初始化 MVC 的环境
     */
    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }


    public void tearDown() {
    }


    @Test
    public void login() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("123456");
        ResultActions actions = mockMvc.perform(post("/auth/login")
                .content(JSONObject.toJSONString(loginRequest))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        Result<LoginResponse> result = JSONObject.parseObject(resultJson, new TypeReference<Result<LoginResponse>>(){});
        Assert.assertNotNull("登录失败", result.getData().getAccessToken());
    }

    @Test
    public void loginFail() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin2");
        loginRequest.setPassword("admin123");
        ResultActions actions = mockMvc.perform(post("/auth/login")
                .content(JSONObject.toJSONString(loginRequest))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        Result<LoginResponse> result = JSONObject.parseObject(resultJson, new TypeReference<Result<LoginResponse>>(){});
        Assert.assertEquals("该用户名未注册", result.getMessage());
    }
}