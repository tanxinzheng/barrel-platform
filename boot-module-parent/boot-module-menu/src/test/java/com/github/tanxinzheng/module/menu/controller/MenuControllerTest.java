package com.github.tanxinzheng.module.menu.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.tanxinzheng.test.TestAppController;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tanxinzheng on 2019/2/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MenuControllerTest extends TestAppController {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMenuList() throws Exception {
        ResultActions actions = mockMvc.perform(get("/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.parseArray(resultJson);
        Assert.assertNotNull("查询菜单列表，测试不通过", jsonArray);
    }

    @Test
    public void getMenuById() throws Exception {
    }

    @Test
    public void createMenu() throws Exception {
    }

    @Test
    public void updateMenu() throws Exception {
    }

    @Test
    public void deleteMenu() throws Exception {
    }

    @Test
    public void deleteMenus() throws Exception {
    }

}