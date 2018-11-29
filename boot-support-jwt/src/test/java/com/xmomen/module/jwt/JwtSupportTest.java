package com.xmomen.module.jwt;

import com.xmomen.module.jwt.support.JwtUser;
import com.xmomen.module.jwt.support.RestResponse;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanxinzheng on 2018/9/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JwtStartApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class JwtSupportTest {

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
        String url = String.format("http://localhost:%d/", port);
        this.base = new URL(url);
    }

    private String getToken(){
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username", "admin");
        multiValueMap.add("password", "123456");
        Map data = testRestTemplate.postForObject(this.base.toString() + "/login", multiValueMap, Map.class);
        Map result = (HashMap) data.get("data");
        String token = String.valueOf(result.get("token"));
        return token;
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
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username", "admin");
        multiValueMap.add("password", "123456");
        Map data = testRestTemplate.postForObject(this.base.toString() + "/login", multiValueMap, Map.class);
        String token = String.valueOf(data.get("token"));
        Assert.assertNotNull(token);
        JwtUser jwtUser = testRestTemplate.getForObject(this.base.toString() + "/user?token=" + token, JwtUser.class);
        Assert.assertNotNull(jwtUser);
    }

    @Test
    public void testAnonymousAccess() throws Exception {
        String data = testRestTemplate.getForObject(this.base.toString() + "/anonymous", String.class);
        Assert.assertEquals(data, "hello");
    }

    @Test
    public void testUserAccessByTokenParam() throws Exception {
        String token = getToken();
        JwtUser jwtUser = testRestTemplate.getForObject(this.base.toString() + "/user?token={token}", JwtUser.class, token);
        Assert.assertNotNull(jwtUser.getId());
    }

    @Test
    public void testUserAccessByTokenHeader() throws Exception {
        headerLogin();
        JwtUser jwtUser = testRestTemplate.getForObject(this.base.toString() + "/user", JwtUser.class);
        Assert.assertNotNull(jwtUser.getId());
    }

    @Test
    public void testUserAccessForbidden() throws Exception {
        headerLogin();
        RestResponse responseEntity = testRestTemplate.getForObject(this.base.toString() + "/admin/user", RestResponse.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertTrue("测试禁止访问管理员权限", responseEntity.getStatus() == 403);
    }

    @Ignore
    @Test
    public void testJwtLoginFail() throws Exception {
        testRestTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                Assert.assertTrue(response.getRawStatusCode() == 401);
            }
        });
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username", "admin");
        multiValueMap.add("password", "errorpassword");
        testRestTemplate.postForObject(this.base.toString() + "/login", multiValueMap, Map.class);
    }

}