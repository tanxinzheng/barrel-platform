package com.github.tanxinzheng.module.jwt;

import com.github.tanxinzheng.module.jwt.support.JwtErrorCode;
import com.github.tanxinzheng.module.jwt.support.RestResponse;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
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
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username", "admin");
        multiValueMap.add("password", "123456");
        Map data = testRestTemplate.postForObject(this.base.toString() + "/login", multiValueMap, Map.class);
        Map result = (HashMap) data.get("data");
        String token = String.valueOf(result.get("accessToken"));
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
        String jwtUser = testRestTemplate.getForObject(this.base.toString() + "/user?token=" + token, String.class);
        Assert.assertNotNull(jwtUser);
    }

    @Test
    public void testAnonymousAccess() throws Exception {
        String data = testRestTemplate.getForObject(this.base.toString() + "/anonymous", String.class);
        Assert.assertEquals("测试匿名访问资源", "hello", data);
    }

    @Test
    public void testUserAccessByTokenParam() throws Exception {
        String token = getToken();
        String name = testRestTemplate.getForObject(this.base.toString() + "/user?token={token}", String.class, token);
        Assert.assertNotNull(name);
        Assert.assertEquals("测试token参数请求访问资源", "管理员", name);
    }

    @Test
    public void testUserAccessByTokenHeader() throws Exception {
        headerLogin();
        String name = testRestTemplate.getForObject(this.base.toString() + "/user?id=123456", String.class);
        Assert.assertNotNull(name);
        Assert.assertEquals("测试token请求头访问资源", "管理员", name);
    }

    @Test
    public void testUserAccessByTokenError() throws Exception {
        String token = getToken();
        token = token + 1;
        RestResponse responseEntity = testRestTemplate.getForObject(this.base.toString() + "/user?token={token}", RestResponse.class, token);
        Assert.assertNotNull(responseEntity);
        Assert.assertTrue("测试错误的token请求", responseEntity.getStatus() == 401);
        Assert.assertEquals("测试错误的token请求", JwtErrorCode.TOKEN_INVALID.getCode(), responseEntity.getCode());
    }

    @Test
    public void testUserAccessNoControlResource() throws Exception {
        headerLogin();
        String email = testRestTemplate.getForObject(this.base.toString() + "/email", String.class);
        Assert.assertNotNull(email);
        Assert.assertEquals("测试未控制资源可访问权限", "admin@xmomen.com", email);
    }


    /**
     * 测试未授权资源访问受限
     * @throws Exception
     */
    @Test
    public void testUserAccessForbidden() throws Exception {
        headerLogin();
        RestResponse responseEntity = testRestTemplate.getForObject(this.base.toString() + "/admin/user?id=123456", RestResponse.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertTrue("测试未授权资源访问受限", responseEntity.getStatus() == 403);
    }

    /**
     * 测试错误密码
     * @throws Exception
     */
    @Test
    public void testJwtLoginFail() throws Exception {
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username", "admin");
        multiValueMap.add("password", "errorpassword");
        RestResponse result = testRestTemplate.postForObject(this.base.toString() + "/login", multiValueMap, RestResponse.class);
        Assert.assertNotNull(result);
        Assert.assertTrue("测试错误密码登录",result.getStatus() == 401);
    }

    /**
     * 测试未授权资源访问受限
     * @throws Exception
     */
    @Test
    public void testTokenExpire() throws Exception {
        jwtConfigProperties.setExpiration(Long.valueOf(5000));
        headerLogin();
        Thread.sleep(10000);
        RestResponse responseEntity = testRestTemplate.getForObject(this.base.toString() + "/user?id=123456", RestResponse.class);
        Assert.assertNotNull(responseEntity);
        Assert.assertTrue("测试token过期", responseEntity.getStatus() == 401);
        Assert.assertEquals("测试token过期", JwtErrorCode.TOKEN_EXPIRATION.getCode(), responseEntity.getCode());
    }

}