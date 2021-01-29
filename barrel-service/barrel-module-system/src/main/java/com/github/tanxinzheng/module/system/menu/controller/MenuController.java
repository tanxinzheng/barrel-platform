package com.github.tanxinzheng.module.system.menu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.framework.mybatis.domian.QueryParams;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.system.menu.domain.dto.MenuDTO;
import com.github.tanxinzheng.module.system.menu.domain.entity.MenuDO;
import com.github.tanxinzheng.module.system.menu.domain.vo.MenuVO;
import com.github.tanxinzheng.module.system.menu.service.MenuService;
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
 * @Date   2021-1-28 11:48:38
 */
@Slf4j
@Api(tags = "菜单接口")
@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Resource
    MenuService menuService;

    /**
     * 分页查询菜单集合
     * @param queryParams
     * @return
     */
    @ApiOperation(value = "分页查询菜单")
    @GetMapping
    public IPage<MenuVO> findPage(QueryParams<MenuDO> queryParams){
        IPage<MenuDTO> page = menuService.findPage(queryParams.getPage(), queryParams.getQueryWrapper());
        return BeanCopierUtils.copy(page, MenuVO.class);
    }

    /**
     * 主键查询菜单
     * @param   id  主键
     * @return  MenuResponse   菜单领域对象
     */
    @ApiOperation(value = "主键查询菜单")
    @GetMapping(value = "/{id}")
    public MenuVO findById(@PathVariable(value = "id") String id){
        MenuDTO menuDTO = menuService.findById(id);
        return BeanCopierUtils.copy(menuDTO, MenuVO.class);
    }

    /**
     * 新增菜单
     * @param menuDTO
     * @return
     */
    @ApiOperation(value = "新增菜单")
    @PostMapping
    public MenuVO create(@RequestBody @Valid MenuDTO menuDTO) {
        menuDTO = menuService.createMenu(menuDTO);
        return BeanCopierUtils.copy(menuDTO, MenuVO.class);
    }

    /**
     * 更新菜单
     * @param id    主键
     * @param menuDTO  更新对象参数
     * @return  MenuResponse   菜单领域对象
     */
    @ApiOperation(value = "更新菜单")
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable(value = "id") String id,
                              @RequestBody @Valid MenuDTO menuDTO){
        if(StringUtils.isNotBlank(id)){
            menuDTO.setId(id);
        }
        return menuService.updateMenu(menuDTO);
    }

    /**
     *  删除菜单
     * @param id    主键
     */
    @ApiOperation(value = "删除单个菜单")
    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable(value = "id") String id){
        return menuService.deleteMenu(id);
    }

    /**
     *  删除菜单
     * @param ids    查询参数对象
     */
    @ApiOperation(value = "批量删除菜单")
    @DeleteMapping
    public boolean batchDelete(@RequestBody List<String> ids){
        AssertValid.notEmpty(ids, "数组参数不能为空");
        return menuService.deleteMenu(ids);
    }


}
