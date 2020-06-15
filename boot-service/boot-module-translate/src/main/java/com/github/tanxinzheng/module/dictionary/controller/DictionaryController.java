package com.github.tanxinzheng.module.dictionary.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.tanxinzheng.framework.mybatis.domian.QueryParams;
import com.github.tanxinzheng.module.dictionary.domain.dto.DictionaryResponse;
import com.github.tanxinzheng.module.dictionary.domain.entity.Dictionary;
import com.github.tanxinzheng.module.dictionary.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = "数据字典")
@RestController
@RequestMapping(value = "/dictionary")
public class DictionaryController {

    @Resource
    DictionaryService dictionaryService;

    /**
     * 数据字典列表
     * @param   queryParams    数据字典查询参数对象
     * @return  Page<DictionaryResponse> 数据字典领域分页对象
     */
    @ApiOperation(value = "查询数据字典列表")
    @GetMapping
    public Page<Dictionary> findPage(QueryParams queryParams){
        return dictionaryService.findPage(queryParams);
    }

    /**
     * 查询单个数据字典
     * @param   id  主键
     * @return  DictionaryResponse   数据字典领域对象
     */
    @ApiOperation(value = "查询数据字典")
    @GetMapping(value = "/{id}")
    public DictionaryResponse findOne(@PathVariable(value = "id") String id){
        return dictionaryService.findOne(id);
    }

    /**
     * 新增数据字典
     * @param   dictionaryCreate  新增对象参数
     * @return  DictionaryResponse   数据字典领域对象
     */
    @ApiOperation(value = "新增数据字典")
    @PostMapping
    public DictionaryResponse add(@RequestBody @Valid Dictionary dictionaryCreate) {
        return dictionaryService.add(dictionaryCreate);
    }

    /**
     * 更新数据字典
     * @param id    主键
     * @param dictionaryUpdate  更新对象参数
     * @return  DictionaryResponse   数据字典领域对象
     */
    @ApiOperation(value = "更新数据字典")
    @PutMapping(value = "/{id}")
    public DictionaryResponse updateDictionary(@PathVariable(value = "id") String id,
                           @RequestBody @Valid Dictionary dictionaryUpdate){
        if(StringUtils.isNotBlank(id)){
            dictionaryUpdate.setId(id);
        }
        return dictionaryService.edit(dictionaryUpdate);
    }

    /**
     *  删除数据字典
     * @param id    主键
     */
    @ApiOperation(value = "删除单个数据字典")
    @DeleteMapping(value = "/{id}")
    public int deleteDictionary(@PathVariable(value = "id") String id){
        return dictionaryService.remove(id);
    }

    /**
     *  删除数据字典
     * @param ids    查询参数对象
     */
    @ApiOperation(value = "批量删除数据字典")
    @DeleteMapping
    public int deleteDictionaries(@RequestBody List<String> ids){
        return dictionaryService.batchRemove(ids);
    }

}
