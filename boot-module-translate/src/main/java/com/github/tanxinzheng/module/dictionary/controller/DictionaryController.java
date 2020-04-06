package com.github.tanxinzheng.module.dictionary.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.tanxinzheng.module.dictionary.domain.dto.DictionaryRequest;
import com.github.tanxinzheng.module.dictionary.domain.dto.DictionaryResponse;
import com.github.tanxinzheng.module.dictionary.service.DictionaryService;
import com.github.tanxinzheng.module.dictionary.domain.entity.Dictionary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@Api(tags = "数据字典接口")
@RestController
@RequestMapping(value = "/dictionary")
public class DictionaryController {

    @Autowired
    DictionaryService dictionaryService;

    /**
     * 数据字典列表
     * @param   dictionaryRequest    数据字典查询参数对象
     * @return  Page<DictionaryResponse> 数据字典领域分页对象
     */
    @ApiOperation(value = "查询数据字典列表")
    @GetMapping
    public Page<DictionaryResponse> getDictionaryList(DictionaryRequest dictionaryRequest){
        return dictionaryService.findPageDictionaryResponse(dictionaryRequest);
    }

    /**
     * 查询单个数据字典
     * @param   id  主键
     * @return  DictionaryResponse   数据字典领域对象
     */
    @ApiOperation(value = "查询数据字典")
    @GetMapping(value = "/{id}")
    public DictionaryResponse getDictionaryById(@PathVariable(value = "id") String id){
        return dictionaryService.findOneDictionaryResponse(id);
    }

    /**
     * 新增数据字典
     * @param   dictionaryCreate  新增对象参数
     * @return  DictionaryResponse   数据字典领域对象
     */
    @ApiOperation(value = "新增数据字典")
    @PostMapping
    public DictionaryResponse createDictionary(@RequestBody @Valid Dictionary dictionaryCreate) {
        return dictionaryService.createDictionary(dictionaryCreate);
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
        return dictionaryService.updateDictionary(dictionaryUpdate);
    }

    /**
     *  删除数据字典
     * @param id    主键
     */
    @ApiOperation(value = "删除单个数据字典")
    @DeleteMapping(value = "/{id}")
    public int deleteDictionary(@PathVariable(value = "id") String id){
        return dictionaryService.deleteDictionary(id);
    }

    /**
     *  删除数据字典
     * @param ids    查询参数对象
     */
    @ApiOperation(value = "批量删除数据字典")
    @DeleteMapping
    public int deleteDictionarys(@RequestBody List<String> ids){
        return dictionaryService.deleteDictionary(ids);
    }


}
