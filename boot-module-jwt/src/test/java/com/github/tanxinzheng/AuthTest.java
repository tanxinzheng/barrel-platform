package com.github.tanxinzheng;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import com.github.tanxinzheng.framework.web.model.RestResponse;
import com.github.tanxinzheng.jwt.config.JwtConfigProperties;
import com.github.tanxinzheng.jwt.controller.dto.LoginRequest;
import com.github.tanxinzheng.jwt.controller.dto.LoginResponse;
import com.github.tanxinzheng.jwt.support.TokenType;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppTestRun.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthTest {
    /**
     * Interface to provide configuration for a web application.
     */

    /**
     * @LocalServerPort 提供了 @Value("${local.server.port}") 的代替
     */
    @LocalServerPort
    private int port;

    private URL base;

    private RestTemplate testRestTemplate;

    @Autowired
    JwtConfigProperties jwtConfigProperties;

    @Before
    public void setUp() throws MalformedURLException {
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

    private String getToken(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("123456");
        String data = testRestTemplate.postForObject(this.base.toString() + "/auth/login", loginRequest, String.class);
        RestResponse<LoginResponse> response = JSONObject.parseObject(data, new TypeReference<RestResponse<LoginResponse>>(){});
        return TokenType.BEARER.getCode() + " " + response.getData().getAccessToken();
    }

    private void headerLogin(){
        String token = getToken();
        testRestTemplate.setInterceptors(Lists.newArrayList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
                requestWrapper.getHeaders().add(jwtConfigProperties.getTokenHeaderName(), token);
                return execution.execute(requestWrapper, body);
            }
        }));
    }

    @Test
    public void testJwtLoginSuccess() throws Exception {
        headerLogin();
        String data =  testRestTemplate.getForObject(this.base.toString() + "/test/currentUser", String.class);
        RestResponse<CurrentLoginUser> response = JSONObject.parseObject(data, new TypeReference<RestResponse<CurrentLoginUser>>(){});
        Assert.assertNotNull(response.getData());
        Assert.assertEquals("admin", response.getData().getUsername());
    }


    @Test
    public void testProtectAccess() throws Exception {
        headerLogin();
        String data = testRestTemplate.getForObject(this.base.toString() + "/test/protect", String.class);
        RestResponse<String> response = JSONObject.parseObject(data, new TypeReference<RestResponse<String>>(){});
        Assert.assertEquals("测试访问已保护资源", "成功访问已保护资源", response.getData());
    }

    @Test
    public void testAnonymousAccess() throws Exception {
        String data = testRestTemplate.getForObject(this.base.toString() + "/test/anonymous", String.class);
        RestResponse<String> response = JSONObject.parseObject(data, new TypeReference<RestResponse<String>>(){});
        Assert.assertEquals("测试匿名访问资源", "成功访问匿名资源", response.getData());
    }

}
