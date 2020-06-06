package com.github.tanxinzheng.module.scheduler.controller;

public class ScheduleTaskControllerTest  {
//
//    private static ScheduleTaskModel scheduleTaskModel;
//
//    @Before
//    public void setUp() throws Exception {
//        super.setUp();
//    }
//
//    @After
//    public void setDown() throws Exception {
//
//    }
//
//    @Test
//    public void test00() throws Exception {
//        ResultActions actions = mockMvc.perform(get("/schedule/task")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//        String resultJson = actions.andReturn().getResponse().getContentAsString();
//        JSONArray jsonArray = JSONArray.parseArray(resultJson);
//        Assert.assertTrue("查询定时任务，测试不通过", jsonArray.size() > 0);
//        scheduleTaskModel = JSONObject.parseObject(jsonArray.get(0).toString(), ScheduleTaskModel.class);
//        Assert.assertNotNull(scheduleTaskModel);
//    }
//
//    @Test
//    public void test01() throws Exception {
//        ScheduleTaskModel model = new ScheduleTaskModel();
//        model.setDescription("测试新增定时任务");
//        model.setJobName("JOB_TEST_ADD_" + UUIDGenerator.getInstance().getUUID(5));
//        model.setJobGroup("JOB_TEST");
//        model.setTriggerName("TRIGGER_TEST_ADD_" + UUIDGenerator.getInstance().getUUID(5));
//        model.setTriggerGroup("TRIGGER_TEST");
//        model.setJobClassName("com.github.tanxinzheng.module.scheduler.job.SimpleJob");
//        model.setCronExpression("0/10 * * * * ?");
//        ResultActions actions = mockMvc.perform(post("/schedule/task")
//                .content(JSONObject.toJSONString(model))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//        String resultJson = actions.andReturn().getResponse().getContentAsString();
//        ScheduleTaskModel newObject = JSONObject.parseObject(resultJson, ScheduleTaskModel.class);
//        Assert.assertNotNull("新增定时任务，测试不通过", newObject);
//    }
//
//    @Test
//    public void test02() throws Exception {
//        ResultActions actions = mockMvc.perform(put("/schedule/task/{jobName}", scheduleTaskModel.getJobName())
//                .param("action", "1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void test03() throws Exception {
//        ResultActions actions = mockMvc.perform(put("/schedule/task/{jobName}", scheduleTaskModel.getJobName())
//                .param("action", "2")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void test04() throws Exception {
//        ResultActions actions = mockMvc.perform(put("/schedule/task/{jobName}", scheduleTaskModel.getJobName())
//                .param("action", "4")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void test09() throws Exception {
//        String jobName = scheduleTaskModel.getJobName();
////        String jobName = "SIMPLE_JOB";
//        ResultActions actions = mockMvc.perform(delete("/schedule/task/{jobName}", jobName)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }

}