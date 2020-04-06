package com.github.tanxinzheng.framework.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.github.tanxinzheng.framework.core.service.AuthManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class DefaultFillObjectHandler  implements MetaObjectHandler {

    @Resource
    AuthManager authManager;

    @Override
    public void insertFill(MetaObject metaObject) {
        String userId = authManager.getCurrentUserId();
        if (StringUtils.isNotBlank(userId)) {
            log.info("开始填充创建者CreateUser");
            this.setInsertFieldValByName("createdUserId", userId, metaObject);
            log.info("开始填充更新者UpdateUser");
            this.setUpdateFieldValByName("updatedUserId", new Date(), metaObject);
        }
        log.info("开始填充插入时间InsertTime");
        this.setInsertFieldValByName("createdTime", new Date(), metaObject);
        log.info("开始填充更新时间UpdateTime");
        this.setUpdateFieldValByName("updatedTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String userId = authManager.getCurrentUserId();
        if (StringUtils.isNotBlank(userId)) {
            log.info("开始填充更新者UpdateUser");
            this.setUpdateFieldValByName("updatedUserId", new Date(), metaObject);
        }
        log.info("开始填充更新时间UpdateTime");
        this.setUpdateFieldValByName("updatedTime", new Date(), metaObject);
    }
}
