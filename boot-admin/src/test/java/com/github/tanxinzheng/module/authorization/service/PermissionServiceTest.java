package com.github.tanxinzheng.module.authorization.service;

import com.github.tanxinzheng.config.ApplicationStart;
import com.github.tanxinzheng.test.TestAppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStart.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PermissionServiceTest extends TestAppService {

    @Test
    public void autoInitPermissions() {

    }
}