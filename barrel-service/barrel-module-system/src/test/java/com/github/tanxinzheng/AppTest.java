package com.github.tanxinzheng;

import com.github.tanxinzheng.framework.secure.config.SecureProperties;
import com.github.tanxinzheng.framework.utils.UUIDGenerator;
import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import com.github.tanxinzheng.module.system.SystemApplication;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SystemApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("fss")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppTest {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    /**
     * @LocalServerPort 提供了 @Value("${local.server.port}") 的代替
     */
    @LocalServerPort
    int port;

    URL base;

    @Resource
    RedisTemplate redisTemplate;

    private static String accessToken = "";

    @Resource
    SecureProperties secureProperties;

    RestTemplate testRestTemplate;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        testRestTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setOutputStreaming(false);
        testRestTemplate.setRequestFactory(clientHttpRequestFactory);
        testRestTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
            }
        });
        String url = String.format("http://localhost:%d/", port);
        this.base = new URL(url);
    }

    public String createToken(){
        accessToken = UUIDGenerator.getInstance().getUUID(32);
        login();
        return accessToken;
    }

    public void login(){
//        Map<String, String> loginRequest = Maps.newHashMap();
//        loginRequest.put("username", "admin");
//        loginRequest.put("password", "123456");
//        String json = testRestTemplate.postForObject("http://localhost:9999/barrel-auth/auth/login", loginRequest, String.class);
        CurrentLoginUser currentLoginUser = CurrentLoginUser.builder()
                .email("test@tanxz.com")
                .id(UUIDGenerator.getInstance().getUUID(32))
                .name("junit test")
                .username("junit")
                .build();
        redisTemplate.opsForValue().set(secureProperties.getTokenHeaderName() + ":" + accessToken, currentLoginUser, 3, TimeUnit.HOURS);
    }

}
