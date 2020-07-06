package com.github.tanxinzheng.module.system.dictionary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.system.dictionary.domain.dto.DictionaryDTO;
import com.github.tanxinzheng.module.system.dictionary.domain.entity.DictionaryDO;
import com.github.tanxinzheng.module.system.dictionary.mapper.DictionaryMapper;
import com.github.tanxinzheng.module.system.dictionary.service.DictionaryService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 14:25:34
 */
@Slf4j
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, DictionaryDO> implements DictionaryService {

    @Resource
    DictionaryMapper dictionaryMapper;

    /**
     * 新增数据字典
     *
     * @param dictionaryDTO
     * @return DictionaryResponse
     */
    @Transactional
    @Override
    public DictionaryDTO createDictionary(DictionaryDTO dictionaryDTO) {
        AssertValid.notNull(dictionaryDTO, "dictionaryDTO参数不能为空");
        DictionaryDO dictionary = dictionaryDTO.toDO(DictionaryDO.class);
        boolean isOk = save(dictionary);
        if(!isOk){
            return null;
        }
        return BeanCopierUtils.copy(dictionary, DictionaryDTO.class);
    }


    /**
     * 更新数据字典
     *
     * @param dictionaryDTO
     * @return DictionaryResponse
     */
    @Transactional
    @Override
    public boolean updateDictionary(DictionaryDTO dictionaryDTO) {
        AssertValid.notNull(dictionaryDTO, "dictionaryDTO不能为空");
        DictionaryDO dictionaryDO = BeanCopierUtils.copy(dictionaryDTO, DictionaryDO.class);
        return updateById(dictionaryDO);
    }

    /**
     * 批量新增数据字典
     * @param dictionarys
     * @return
     */
    @Transactional
    @Override
    public List<DictionaryDTO> createDictionarys(List<DictionaryDTO> dictionarys) {
        AssertValid.notEmpty(dictionarys, "dictionarys参数不能为空");
        List<DictionaryDO> dictionaryDOList = BeanCopierUtils.copy(dictionarys, DictionaryDO.class);
        boolean isOK = saveBatch(dictionaryDOList);
        if(!isOK){
            return Lists.newArrayList();
        }
        List<String> ids = dictionaryDOList.stream().map(DictionaryDO::getId).collect(Collectors.toList());
        List<DictionaryDO> data = dictionaryMapper.selectBatchIds(ids);
        return BeanCopierUtils.copy(data, DictionaryDTO.class);
    }


    /**
     * 主键查询对象
     *
     * @param id
     * @return DictionaryResponse
     */
    @Override
    public DictionaryDTO findById(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        DictionaryDO dictionary = getById(id);
        return BeanCopierUtils.copy(dictionary, DictionaryDTO.class);
    }

    /**
    * 查询集合对象
    *
    * @param queryWrapper
    * @return List<DictionaryDTO>
    */
    @Override
    public List<DictionaryDTO> findList(QueryWrapper<DictionaryDO> queryWrapper) {
        return BeanCopierUtils.copy(list(queryWrapper), DictionaryDTO.class);
    }
    /**
     * 查询数据字典领域分页对象
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<DictionaryDTO> findPage(IPage<DictionaryDO> page, QueryWrapper<DictionaryDO> queryWrapper) {
        IPage<DictionaryDO> data = (Page<DictionaryDO>) page(page, queryWrapper);
        return BeanCopierUtils.copy(data, DictionaryDTO.class);
    }

    /**
     * 批量删除数据字典
     *
     * @param ids
     * @return int
     */
    @Transactional
    @Override
    public boolean deleteDictionary(List<String> ids) {
        AssertValid.notEmpty(ids, "ids参数不能为空");
        return removeByIds(ids);
    }

    /**
     * 删除数据字典
     * @param  id
     * @return
     */
    @Transactional
    @Override
    public boolean deleteDictionary(String id) {
        AssertValid.notNull(id, "id参数不能为空");
        return removeById(id);
    }
}
