package com.github.tanxinzheng.module.auth.feign;

import com.github.tanxinzheng.framework.secure.domain.AuthUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Hello world!
 *
 */
@FeignClient(value = "barrel-attachment")
@Api(tags = {"Feign接口", "附件服务"})
public interface IAttachmentClient {

    /**
     * 查询用户
     * @param username
     * @return
     */
    @ApiOperation(value = "查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名")
    })
    @GetMapping(value = "/user-info/username")
    AuthUser getUserByUsername(@RequestParam(value = "username") String username);

}
