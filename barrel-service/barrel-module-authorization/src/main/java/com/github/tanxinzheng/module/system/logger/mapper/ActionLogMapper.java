package com.github.tanxinzheng.module.system.logger.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.tanxinzheng.module.system.logger.domain.entity.ActionLogDO;
import org.apache.ibatis.annotations.Mapper;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-7-7 10:42:23
 */
@Mapper
public interface ActionLogMapper extends BaseMapper<ActionLogDO> {

}