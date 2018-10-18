package com.xmomen.module.jwt;

import com.xmomen.module.jwt.support.JwtUser;
import com.xmomen.module.jwt.support.RestResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.MalformedURLException;
import java.net.URL;
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

    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp() throws MalformedURLException {
        testRestTemplate = new TestRestTemplate();
        String url = String.format("http://localhost:%d/", port);
        this.base = new URL(url);
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
    public void testUserAccess() throws Exception {
        JwtUser jwtUser = testRestTemplate.getForObject(this.base.toString() + "/user", JwtUser.class);
        Assert.assertNull(jwtUser.getId());
    }

    @Test
    public void testJwtLoginFail() throws Exception {
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username", "admin");
        multiValueMap.add("password", "1234567");
        String data = testRestTemplate.postForObject(this.base.toString() + "/login", multiValueMap, String.class);
        Assert.assertNotNull(data);
//        Assert.assertTrue(data.getStatus() == 401);
    }

}