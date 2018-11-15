package com.github.tanxinzheng;

import com.alibaba.fastjson.JSONArray;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppTestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppTest {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testDictionaryInterprenter() throws Exception {
        ResultActions actions = mockMvc.perform(get("/demo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.parseArray(resultJson);
        Assert.notEmpty(jsonArray, "测试不通过");
        Assert.isTrue(jsonArray.getJSONObject(0).get("sexDesc").equals("女"), "测试不通过");
        Assert.isTrue(jsonArray.getJSONObject(1).get("sexDesc").equals("男"), "测试不通过");
        Assert.isTrue(jsonArray.getJSONObject(0).get("disableName").equals("启用"), "测试不通过");
        Assert.isTrue(jsonArray.getJSONObject(1).get("disableName").equals("禁用"), "测试不通过");
        Assert.isTrue(jsonArray.getJSONObject(0).getJSONObject("account").get("userName").equals("管理员"), "测试不通过");
    }
}
