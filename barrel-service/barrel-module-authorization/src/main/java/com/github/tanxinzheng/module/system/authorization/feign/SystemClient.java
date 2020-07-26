package com.github.tanxinzheng.module.system.authorization.feign;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.tanxinzheng.framework.model.Result;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.secure.domain.AuthUser;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.system.attachment.domain.dto.AttachmentDTO;
import com.github.tanxinzheng.module.system.attachment.service.AttachmentService;
import com.github.tanxinzheng.module.system.feign.ISystemClient;
import com.github.tanxinzheng.module.system.authorization.domain.dto.RoleDTO;
import com.github.tanxinzheng.module.system.authorization.domain.dto.UserDTO;
import com.github.tanxinzheng.module.system.authorization.domain.entity.UserDO;
import com.github.tanxinzheng.module.system.authorization.mapper.UserMapper;
import com.github.tanxinzheng.module.system.authorization.service.UserRoleRelationService;
import com.github.tanxinzheng.module.system.authorization.service.UserService;
import com.github.tanxinzheng.module.system.feign.domain.response.AttachmentResponse;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Date 2020/6/27
 */
@ApiIgnore
@RestController
public class SystemClient implements ISystemClient {

    @Resource
    UserService userService;
    @Resource
    UserMapper userMapper;
    @Resource
    UserRoleRelationService userRoleRelationService;
    @Resource
    AttachmentService attachmentService;
    /**
     * 查询用户
     *
     * @param username
     * @return
     */
    @Override
    public Result<AuthUser> getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserDO::getUsername, username)
                .or()
                .eq(UserDO::getEmail, username)
                .or()
                .eq(UserDO::getPhoneNumber, username);
        UserDO userDO = userMapper.selectOne(lambdaQueryWrapper);
        AuthUser authUser = BeanCopierUtils.copy(userDO, AuthUser.class);
        authUser.setRoles(Lists.newArrayList());
        return Result.success(authUser);
    }

    /**
     * 查询主键用户
     *
     * @param userId
     * @return
     */
    @Override
    public Result<AuthUser> getUserByUserId(String userId) {
        AssertValid.notBlank(userId, "主键查询参数不能为空");
        UserDTO userDTO = userService.findById(userId);
        AuthUser authUser = BeanCopierUtils.copy(userDTO, AuthUser.class);
        return Result.success(authUser);
    }

    /**
     * 查询用户角色
     *
     * @param userId
     * @return
     */
    @Override
    public Result<List<String>> getRoles(String userId) {
        List<RoleDTO> list = userRoleRelationService.findUserRole(userId, true);
        List<String> roles = Lists.newArrayList();
        if(CollectionUtils.isEmpty(list)){
            return Result.success(roles);
        }
        list.forEach(roleDTO -> {
            roles.add(roleDTO.getRoleCode());
        });
        return Result.success(roles);
    }

    /**
     * 查询附件信息
     *
     * @param fileKey
     * @return
     */
    @Override
    public Result<AttachmentResponse> selectByFileKey(String fileKey) {
        AttachmentDTO attachmentDTO = attachmentService.findByFileKey(fileKey);
        return Result.success(BeanCopierUtils.copy(attachmentDTO, AttachmentResponse.class));
    }

    /**
     * 上传附件
     *
     * @param file
     * @param group
     * @param owner
     * @param relationId
     * @return
     */
    @Override
    public Result<String> uploadAttachment(MultipartFile file, String group, String owner, String relationId) {
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setUploadTime(LocalDateTime.now());
        attachmentDTO.setIsDelete(Boolean.FALSE);
        attachmentDTO.setMultipartFile(file);
        attachmentDTO.setAttachmentGroup(group);
        attachmentDTO.setRelationId(relationId);
        attachmentDTO.setOwner(owner);
        attachmentDTO = attachmentService.createAttachment(attachmentDTO);
        return Result.success(attachmentDTO.getAttachmentKey());
    }
}
