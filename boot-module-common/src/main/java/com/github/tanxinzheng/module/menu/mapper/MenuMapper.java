package com.github.tanxinzheng.module.menu.mapper;

import com.github.tanxinzheng.module.menu.model.Menu;
import com.github.tanxinzheng.module.menu.model.MenuModel;
import com.github.tanxinzheng.module.menu.model.MenuQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2019-1-11 22:32:46
 * @version 1.0.0
 */
@Mapper
public interface MenuMapper {

    List<Menu> select(MenuQuery menuQuery);

    List<MenuModel> selectModel(MenuQuery menuQuery);

    Menu selectByPrimaryKey(String primaryKey);

    MenuModel selectModelByPrimaryKey(String primaryKey);

    int deleteByPrimaryKey(String primaryKey);

    int deletesByPrimaryKey(@Param("ids") List<String> primaryKeys);

    int insertSelective(Menu record);

    int updateSelective(Menu record);

    int updateSelectiveByQuery(@Param("record") Menu record, @Param("query") MenuQuery example);
}
