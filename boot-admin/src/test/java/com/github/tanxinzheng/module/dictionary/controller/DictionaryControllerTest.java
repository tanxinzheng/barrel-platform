package com.github.tanxinzheng.module.dictionary.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.tanxinzheng.AppTest;
import com.github.tanxinzheng.framework.web.model.RestResponse;
import com.github.tanxinzheng.module.dictionary.model.DictionaryModel;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tanxinzheng on 2018/11/20.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DictionaryControllerTest extends AppTest {

    String GROUP_CODE = "TEST_GROUP";

    private static DictionaryModel dictionaryModel;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void setDown() throws Exception {
        deleteById();
    }

    private void deleteById() throws Exception {
        if(dictionaryModel == null){
            return;
        }
        ResultActions actions = mockMvc.perform(delete("/dictionary/{id}", dictionaryModel.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private RestResponse<DictionaryModel> create() throws Exception {
        DictionaryModel model = new DictionaryModel();
        model.setGroupCode(GROUP_CODE);
        model.setGroupName("测试分组");
        model.setDictionaryCode("TEST_0"+ new Random().nextInt());
        model.setDictionaryName("第一测试小组");
        ResultActions actions = mockMvc.perform(post("/dictionary")
                .header(AUTHORIZATION, createToken())
                .content(JSONObject.toJSONString(model))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        RestResponse<DictionaryModel> restResponse = JSONObject.parseObject(resultJson, new TypeReference<RestResponse<DictionaryModel>>(){});
        dictionaryModel = restResponse.getData();
        return restResponse;
    }

    @Test
    public void test0createDictionary() throws Exception {
        RestResponse<DictionaryModel> resultJson = create();
        Assert.assertNotNull("新增数据字典，测试不通过", resultJson);
        Assert.assertNotNull("新增数据字典，测试不通过", resultJson.getData());
        Assert.assertNotNull("新增数据字典，测试不通过", resultJson.getData().getId());
    }

    @Test
    public void test1getDictionaryList() throws Exception {
        ResultActions actions = mockMvc.perform(get("/dictionary")
                .header(AUTHORIZATION, createToken())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        RestResponse<List<DictionaryModel>> restResponse = JSONObject.parseObject(resultJson, new TypeReference<RestResponse<List<DictionaryModel>>>(){});
        Assert.assertNotNull("查询数据字典列表，测试不通过", restResponse);
        Assert.assertTrue("查询数据字典列表，测试不通过", restResponse.getData().size() > 0);
    }

    @Test
    public void test2getDictionaryById() throws Exception {
        create();
        ResultActions actions = mockMvc.perform(get("/dictionary/{id}", dictionaryModel.getId())
                .header(AUTHORIZATION, createToken())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONObject resultObj = JSONObject.parseObject(resultJson);
        Assert.assertNotNull("查询数据字典，测试不通过", resultObj);
        Assert.assertNotNull("查询数据字典，测试不通过",resultObj.get("id"));
    }

    @Test
    public void test3updateDictionary() throws Exception {
        create();
        DictionaryModel model = dictionaryModel;
        String name = "第一测试小组" + new Date().getTime();
        model.setDictionaryName(name);
        ResultActions actions = mockMvc.perform(put("/dictionary/{id}", model.getId())
                .header(AUTHORIZATION, createToken())
                .content(JSONObject.toJSONString(model))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        String resultJson = actions.andReturn().getResponse().getContentAsString();
        JSONObject resultObj = JSONObject.parseObject(resultJson);
        Assert.assertNotNull("新增数据字典，测试不通过", resultObj);
        Assert.assertTrue("新增数据字典，测试不通过", resultObj.get("dictionaryName").equals(name));
    }

    @Test
    public void test6downloadTemplate() throws Exception {
//        ResultActions actions = mockMvc.perform(get("/dictionary/template")
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .accept(MediaType.APPLICATION_OCTET_STREAM_VALUE))
//                .andDo(print())
//                .andExpect(status().isOk());
//        actions.andDo(new ResultHandler() {
//            @Override
//            public void handle(MvcResult result) throws Exception {
//                result.getResponse().setCharacterEncoding("UTF-8");
//                MockHttpServletResponse contentRespon = result.getResponse();
//                InputStream contentInStream = new ByteArrayInputStream(
//                        contentRespon.getContentAsByteArray());
//                FileUtils.copyInputStreamToFile(contentInStream, new File("./src/test/resource/ddd.xls"));
//                Assert.assertEquals("application/ms-excel", contentRespon.getContentType());
//            }
//        });
    }

    @Test
    public void test7exportDictionaries() throws Exception {
    }

    @Test
    public void test8importDictionaries() throws Exception {
    }


    @Test
    public void test9deleteDictionary() throws Exception {
    }

    @Test
    public void test9deleteDictionaries() throws Exception {

    }

}