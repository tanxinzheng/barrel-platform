package com.github.tanxinzheng.module.system.authorization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.tanxinzheng.module.system.authorization.domain.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 15:43:46
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

}