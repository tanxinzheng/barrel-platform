package com.github.tanxinzheng.module.authorization.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.framework.mybatis.domian.QueryParams;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.authorization.domain.dto.UserRoleRelationDTO;
import com.github.tanxinzheng.module.authorization.domain.entity.UserRoleRelationDO;
import com.github.tanxinzheng.module.authorization.domain.vo.UserRoleRelationVO;
import com.github.tanxinzheng.module.authorization.service.UserRoleRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
@Slf4j
@Api(tags = "用户角色关系接口")
@RestController
@RequestMapping(value = "/user-role")
public class UserRoleRelationController {

    @Resource
    UserRoleRelationService userRoleRelationService;

    /**
     * 分页查询用户角色关系集合
     * @param queryParams
     * @return
     */
    @ApiOperation(value = "分页查询用户角色关系")
    @GetMapping
    public IPage<UserRoleRelationVO> findPage(QueryParams<UserRoleRelationDO> queryParams){
        IPage<UserRoleRelationDTO> page = userRoleRelationService.findPage(queryParams.getPage(), queryParams.getQueryWrapper());
        return BeanCopierUtils.copy(page, UserRoleRelationVO.class);
    }

    /**
     * 主键查询用户角色关系
     * @param   id  主键
     * @return  UserRoleRelationResponse   用户角色关系领域对象
     */
    @ApiOperation(value = "主键查询用户角色关系")
    @GetMapping(value = "/{id}")
    public UserRoleRelationVO findById(@PathVariable(value = "id") String id){
        UserRoleRelationDTO userRoleRelationDTO = userRoleRelationService.findById(id);
        return BeanCopierUtils.copy(userRoleRelationDTO, UserRoleRelationVO.class);
    }

    /**
     * 新增用户角色关系
     * @param userRoleRelationDTO
     * @return
     */
    @ApiOperation(value = "新增用户角色关系")
    @PostMapping
    public UserRoleRelationVO create(@RequestBody @Valid UserRoleRelationDTO userRoleRelationDTO) {
        userRoleRelationDTO = userRoleRelationService.createUserRoleRelation(userRoleRelationDTO);
        return BeanCopierUtils.copy(userRoleRelationDTO, UserRoleRelationVO.class);
    }

    /**
     * 更新用户角色关系
     * @param id    主键
     * @param userRoleRelationDTO  更新对象参数
     * @return  UserRoleRelationResponse   用户角色关系领域对象
     */
    @ApiOperation(value = "更新用户角色关系")
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable(value = "id") String id,
                          @RequestBody @Valid UserRoleRelationDTO userRoleRelationDTO){
        if(StringUtils.isNotBlank(id)){
            userRoleRelationDTO.setId(id);
        }
        return userRoleRelationService.updateUserRoleRelation(userRoleRelationDTO);
    }

    /**
     *  删除用户角色关系
     * @param id    主键
     */
    @ApiOperation(value = "删除单个用户角色关系")
    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable(value = "id") String id){
        return userRoleRelationService.deleteUserRoleRelation(id);
    }

    /**
     *  删除用户角色关系
     * @param ids    查询参数对象
     */
    @ApiOperation(value = "批量删除用户角色关系")
    @DeleteMapping
    public boolean batchDelete(@RequestBody List<String> ids){
        AssertValid.notEmpty(ids, "数组参数不能为空");
        return userRoleRelationService.deleteUserRoleRelation(ids);
    }


}
