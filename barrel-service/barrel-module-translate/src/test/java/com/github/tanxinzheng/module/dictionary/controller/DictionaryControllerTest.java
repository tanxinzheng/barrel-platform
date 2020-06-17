package com.github.tanxinzheng.module.dictionary.controller;

import com.github.tanxinzheng.AppTestConfig;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by tanxinzheng on 2018/11/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppTestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DictionaryControllerTest {

    String GROUP_CODE = "TEST_GROUP";


    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
//
//    @After
//    public void setDown() throws Exception {
//        deleteById();
//    }
//
//    protected String createToken(){
//        return "Bearer XMO";
//    }
//
//    private void deleteById() throws Exception {
//        if(dictionaryModel == null){
//            return;
//        }
//        ResultActions actions = mockMvc.perform(delete("/dictionary/{id}", dictionaryModel.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    private Result<DictionaryModel> create() throws Exception {
//        DictionaryModel model = new DictionaryModel();
//        model.setGroupCode(GROUP_CODE);
//        model.setGroupName("测试分组");
//        model.setDictionaryCode("TEST_0"+ new Random().nextInt());
//        model.setDictionaryName("第一测试小组");
//        ResultActions actions = mockMvc.perform(post("/dictionary")
//                .header(AUTHORIZATION, createToken())
//                .content(JSONObject.toJSONString(model))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//        String resultJson = actions.andReturn().getResponse().getContentAsString();
//        Result<DictionaryModel> result = JSONObject.parseObject(resultJson, new TypeReference<Result<DictionaryModel>>(){});
//        dictionaryModel = result.getData();
//        return result;
//    }
//
//    @Test
//    public void test0createDictionary() throws Exception {
//        Result<DictionaryModel> resultJson = create();
//        Assert.assertNotNull("新增数据字典，测试不通过", resultJson);
//        Assert.assertNotNull("新增数据字典，测试不通过", resultJson.getData());
//        Assert.assertNotNull("新增数据字典，测试不通过", resultJson.getData().getId());
//    }
//
//    @Test
//    public void test1getDictionaryList() throws Exception {
//        ResultActions actions = mockMvc.perform(get("/dictionary")
//                .header(AUTHORIZATION, createToken())
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//        String resultJson = actions.andReturn().getResponse().getContentAsString();
//        Result<List<DictionaryModel>> result = JSONObject.parseObject(resultJson, new TypeReference<Result<List<DictionaryModel>>>(){});
//        Assert.assertNotNull("查询数据字典列表，测试不通过", result);
//        Assert.assertTrue("查询数据字典列表，测试不通过", result.getData().size() > 0);
//    }
//
//    @Test
//    public void test2getDictionaryById() throws Exception {
//        create();
//        ResultActions actions = mockMvc.perform(get("/dictionary/{id}", dictionaryModel.getId())
//                .header(AUTHORIZATION, createToken())
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//        String resultJson = actions.andReturn().getResponse().getContentAsString();
//        JSONObject resultObj = JSONObject.parseObject(resultJson);
//        Assert.assertNotNull("查询数据字典，测试不通过", resultObj);
//        Assert.assertNotNull("查询数据字典，测试不通过",resultObj.get("id"));
//    }
//
//    @Test
//    public void test3updateDictionary() throws Exception {
//        create();
//        DictionaryModel model = dictionaryModel;
//        String name = "第一测试小组" + new Date().getTime();
//        model.setDictionaryName(name);
//        ResultActions actions = mockMvc.perform(put("/dictionary/{id}", model.getId())
//                .header(AUTHORIZATION, createToken())
//                .content(JSONObject.toJSONString(model))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//        String resultJson = actions.andReturn().getResponse().getContentAsString();
//        JSONObject resultObj = JSONObject.parseObject(resultJson);
//        Assert.assertNotNull("新增数据字典，测试不通过", resultObj);
//        Assert.assertTrue("新增数据字典，测试不通过", resultObj.get("dictionaryName").equals(name));
//    }

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