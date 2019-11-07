package com.github.tanxinzheng.module.dictionary.mapper;

import com.github.tanxinzheng.module.dictionary.model.Dictionary;
import com.github.tanxinzheng.module.dictionary.model.DictionaryModel;
import com.github.tanxinzheng.module.dictionary.model.DictionaryQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-6-11 1:07:45
 * @version 1.0.0
 */
@Mapper
public interface DictionaryMapper {

    List<Dictionary> select(DictionaryQuery dictionaryQuery);

    List<DictionaryModel> selectModel(DictionaryQuery dictionaryQuery);

    Dictionary selectByPrimaryKey(String primaryKey);

    DictionaryModel selectModelByPrimaryKey(String primaryKey);

    int deleteByPrimaryKey(String primaryKey);

    int deletesByPrimaryKey(@Param("ids") List<String> primaryKeys);

    int insertSelective(Dictionary record);

    int updateSelective(Dictionary record);

    int updateSelectiveByQuery(@Param("record") Dictionary record, @Param("query") DictionaryQuery example);

    void insertByBatch(List<DictionaryModel> list);
}
