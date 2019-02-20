package com.github.tanxinzheng.module.menu.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.tanxinzheng.test.TestAppController;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tanxinzheng on 2019/2/18.
 */
public class AccountMenuControllerTest extends TestAppController {
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAccountMenu() throws Exception {
        ResultActions actions = mockMvc.perform(get("/account/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.parseArray(resultJson);
        Assert.assertNotNull("查询当前用户菜单，测试不通过", jsonArray);
        Assert.assertNotNull("查询当前用户菜单，测试不通过", jsonArray.size()>0);
    }

}