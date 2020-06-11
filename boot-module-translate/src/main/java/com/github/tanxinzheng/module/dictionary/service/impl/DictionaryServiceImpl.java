package com.github.tanxinzheng.module.dictionary.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanxinzheng.framework.model.QueryParams;
import com.github.tanxinzheng.module.dictionary.domain.dto.DictionaryResponse;
import com.github.tanxinzheng.module.dictionary.domain.entity.Dictionary;
import com.github.tanxinzheng.module.dictionary.mapper.DictionaryMapper;
import com.github.tanxinzheng.module.dictionary.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    @Resource
    DictionaryMapper dictionaryMapper;

    /**
     * 新增数据字典
     *
     * @param dictionaryCreate
     * @return DictionaryResponse
     */
    @Transactional
    @Override
    public DictionaryResponse add(Dictionary dictionaryCreate) {
        dictionaryMapper.insert(dictionaryCreate);
        Dictionary dictionary = dictionaryMapper.selectById(dictionaryCreate.getId());
        return DictionaryResponse.toResponse(dictionary);
    }

    /**
     * 批量新增数据字典
     *
     * @param dictionary
     * @return List<DictionaryResponse>
     */
    @Transactional
    @Override
    public List<Dictionary> batchAdd(List<Dictionary> dictionary) {
        dictionaryMapper.insertBatch(dictionary);
        List<String> ids = dictionary.stream().map(Dictionary::getId).collect(Collectors.toList());
        return dictionaryMapper.selectBatchIds(ids);
    }

    /**
     * 更新数据字典
     *
     * @param dictionaryUpdate
     * @return DictionaryResponse
     */
    @Transactional
    @Override
    public DictionaryResponse edit(Dictionary dictionaryUpdate) {
        dictionaryMapper.updateById(dictionaryUpdate);
        Dictionary dictionary = dictionaryMapper.selectById(dictionaryUpdate.getId());
        return DictionaryResponse.toResponse(dictionary);
    }

    /**
     * 根据查询参数查询单个对象
     *
     * @param id
     * @return DictionaryResponse
     */
    @Override
    public DictionaryResponse findOne(String id) {
        Dictionary dictionary = dictionaryMapper.selectById(id);
        return DictionaryResponse.toResponse(dictionary);
    }

    /**
     * 查询数据字典领域分页对象（带参数条件）
     * @return Page<DictionaryResponse>
     */
    @Override
    public Page<Dictionary> findPage(QueryParams queryParams) {
        Page<Dictionary> dictionaryPage = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        return (Page<Dictionary>) dictionaryMapper.selectPage(dictionaryPage, queryParams.getQueryWrapper());
    }

    /**
     * 批量删除数据字典
     *
     * @param ids
     * @return int
     */
    @Transactional
    @Override
    public int batchRemove(List<String> ids) {
        return dictionaryMapper.deleteBatchIds(ids);
    }

    /**
     * 删除数据字典
     *
     * @param id
     * @return int
     */
    @Transactional
    @Override
    public int remove(String id) {
        return dictionaryMapper.deleteById(id);
    }
}
