package com.github.tanxinzheng.module.system.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.tanxinzheng.module.system.menu.domain.entity.MenuDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2021-1-28 11:48:38
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuDO> {

}