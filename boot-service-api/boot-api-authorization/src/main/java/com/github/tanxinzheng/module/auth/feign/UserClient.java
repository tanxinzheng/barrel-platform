package com.github.tanxinzheng.module.auth.feign;

import com.github.tanxinzheng.framework.secure.domain.AuthUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Hello world!
 *
 */
@FeignClient(
        value = "api-authorization",
        fallback = UserClientFallback.class
)
@Api(tags = {"Feign接口", "认证服务"})
//@RestController
//@RequestMapping(value = "/user-info")
public interface UserClient {

    @ApiOperation(value = "查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名")
    })
    @GetMapping(value = "/user-info/username")
    AuthUser getUserByUsername(@RequestParam(value = "username") String username);

    @ApiOperation(value = "查询用户角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户主键")
    })
    @GetMapping(value = "/user-info/{id}/roles")
    List<String> getRoles(@PathVariable(value = "id") String id);

}
