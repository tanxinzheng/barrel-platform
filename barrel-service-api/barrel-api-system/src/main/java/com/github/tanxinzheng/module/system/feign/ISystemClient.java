package com.github.tanxinzheng.module.system.feign;

import com.github.tanxinzheng.framework.model.Result;
import com.github.tanxinzheng.framework.model.TreeNode;
import com.github.tanxinzheng.framework.secure.domain.AuthUser;
import com.github.tanxinzheng.module.system.feign.fallback.UserClientFallbackFactory;
import com.github.tanxinzheng.module.system.feign.domain.response.AttachmentResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Hello world!
 *
 */
@FeignClient(value = "barrel-system", fallbackFactory = UserClientFallbackFactory.class)
@Api(tags = {"Feign接口", "系统服务"})
public interface ISystemClient {

    public static final String CLIENT_API_PREFIX = "/feign-api";

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
    Result<AuthUser> getUserByUsername(@RequestParam(value = "username") String username);

    /**
     * 查询主键用户
     * @param userId
     * @return
     */
    @ApiOperation(value = "根据主键查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID")
    })
    @GetMapping(value = "/user-info/{id}")
    Result<AuthUser> getUserByUserId(@PathVariable(value = "id") String userId);

    /**
     * 查询用户角色
     * @param id
     * @return
     */
    @ApiOperation(value = "查询用户角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户主键")
    })
    @GetMapping(value = "/user-info/{id}/roles")
    Result<List<String>> getRoles(@PathVariable(value = "id") String id);


    /**
     * 查询附件信息
     * @param fileKey
     * @return
     */
    @ApiOperation(value = "查询附件信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileKey", value = "文件Key")
    })
    @GetMapping(value = "/attachment/{fileKey}")
    Result<AttachmentResponse> selectByFileKey(@PathVariable(value = "fileKey") String fileKey);


    /**
     * 上传附件
     * @return
     */
    @ApiOperation(value = "上传附件信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件流"),
            @ApiImplicitParam(name = "group", value = "所属组"),
            @ApiImplicitParam(name = "owner", value = "权限属主：PUBLIC-公共可读，PRIVATE-私人可读，<ROLE>-权限组可读"),
            @ApiImplicitParam(name = "relationId", value = "关联ID")
    })
    @PostMapping(value = "/attachment/upload", produces = { MediaType.MULTIPART_FORM_DATA_VALUE }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    Result<String> uploadAttachment(@RequestPart("file") MultipartFile file,
                                    @RequestParam(value = "group") String group,
                                    @RequestParam(value = "owner") String owner,
                                    @RequestParam(value = "relationId", required = false) String relationId);


    /**
     * 查询树形菜单
     * @param userId
     * @return
     */
    @ApiOperation(value = "查询树形菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId")
    })
    @GetMapping(value = "/menu/tree")
    Result<TreeNode> selectTreeMenu(@RequestParam(value = "userId") String userId);
}
