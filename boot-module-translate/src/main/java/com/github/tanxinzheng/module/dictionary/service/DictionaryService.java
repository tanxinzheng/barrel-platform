package com.github.tanxinzheng.module.dictionary.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.tanxinzheng.module.dictionary.domain.dto.DictionaryRequest;
import com.github.tanxinzheng.module.dictionary.domain.dto.DictionaryResponse;
import com.github.tanxinzheng.module.dictionary.domain.entity.Dictionary;

import java.util.List;


public interface DictionaryService {

    /**
     * 新增数据字典
     * @param dictionaryCreate
     * @return DictionaryResponse
     */
    public DictionaryResponse createDictionary(Dictionary dictionaryCreate);

    /**
     * 批量新增数据字典
     * @param dictionary
     * @return List<Dictionary>
     */
    List<Dictionary> createDictionarys(List<Dictionary> dictionary);

    /**
     * 更新数据字典
     * @param   dictionaryUpdate
     * @return  DictionaryResponse
     */
    public DictionaryResponse updateDictionary(Dictionary dictionaryUpdate);

    /**
     * 根据查询参数查询单个对象
     * @param   id
     * @return  DictionaryResponse
     */
    public DictionaryResponse findOneDictionaryResponse(String id);

    /**
     * 查询数据字典领域分页对象（带参数条件）
     * @param   dictionaryRequest
     * @return  Page<DictionaryResponse>
     */
    public Page<DictionaryResponse> findPageDictionaryResponse(DictionaryRequest dictionaryRequest);

    /**
     * 批量删除数据字典
     * @param  ids
     * @return int
     */
    public int deleteDictionary(List<String> ids);

    /**
     * 删除数据字典
     * @param  id
     * @return int
     */
    public int deleteDictionary(String id);

}
