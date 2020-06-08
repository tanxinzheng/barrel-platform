package com.github.tanxinzheng.module.dictionary.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.tanxinzheng.module.dictionary.domain.dto.DictionaryResponse;
import com.github.tanxinzheng.module.dictionary.domain.entity.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictionaryMapper extends BaseMapper<Dictionary> {

    int insertBatch(@Param("list") List<Dictionary> list);

    Page<DictionaryResponse> findPage(Page<DictionaryResponse> page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
