package com.github.tanxinzheng.module;

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
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppTest extends TestAppController {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void setDown() throws Exception {

    }

    @Test
    public void test01() throws Exception {
        ResultActions actions = mockMvc.perform(get("/schedule/task")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.parseArray(resultJson);
        Assert.assertNotNull("查询任务，测试不通过", jsonArray);
    }

}
