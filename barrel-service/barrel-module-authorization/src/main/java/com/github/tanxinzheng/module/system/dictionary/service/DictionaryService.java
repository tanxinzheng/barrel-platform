package com.github.tanxinzheng.module.system.dictionary.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.module.system.dictionary.domain.dto.DictionaryDTO;
import com.github.tanxinzheng.module.system.dictionary.domain.entity.DictionaryDO;

import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 14:25:34
 */
public interface DictionaryService {

    /**
     * 新增数据字典
     * @param  dictionaryCreate
     * @return DictionaryDTO
     */
    public DictionaryDTO createDictionary(DictionaryDTO dictionaryCreate);

    /**
     * 批量新增数据字典
     * @param dictionary
     * @return List<Dictionary>
     */
    List<DictionaryDTO> createDictionarys(List<DictionaryDTO> dictionary);

    /**
     * 更新数据字典
     * @param   dictionaryUpdate
     * @return  boolean
     */
    public boolean updateDictionary(DictionaryDTO dictionaryUpdate);

    /**
     * 主键查询对象
     * @param   id
     * @return  DictionaryDTO
     */
    public DictionaryDTO findById(String id);


    /**
    * 查询集合对象
    * @param queryWrapper
    * @return List<DictionaryDTO>
    */
    public List<DictionaryDTO> findList(QueryWrapper<DictionaryDO> queryWrapper);

    /**
     * 查询数据字典领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    public IPage<DictionaryDTO> findPage(IPage<DictionaryDO> page, QueryWrapper<DictionaryDO> queryWrapper);

    /**
     * 批量删除数据字典
     * @param  ids
     * @return boolean
     */
    public boolean deleteDictionary(List<String> ids);

    /**
     * 删除数据字典
     * @param  id
     * @return boolean
     */
    public boolean deleteDictionary(String id);

}
