package com.github.tanxinzheng.module.dictionary.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tanxinzheng.framework.mybatis.domian.QueryParams;
import com.github.tanxinzheng.framework.mybatis.utils.BeanCopierUtils;
import com.github.tanxinzheng.framework.utils.AssertValid;
import com.github.tanxinzheng.module.dictionary.domain.dto.DictionaryDTO;
import com.github.tanxinzheng.module.dictionary.domain.entity.DictionaryDO;
import com.github.tanxinzheng.module.dictionary.domain.vo.DictionaryVO;
import com.github.tanxinzheng.module.dictionary.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Email  tanxinzheng@139.com
 * @Date   2020-6-25 14:25:34
 */
@Slf4j
@Api(tags = "数据字典接口")
@RestController
@RequestMapping(value = "/dictionary")
public class DictionaryController {

    @Resource
    DictionaryService dictionaryService;

    /**
     * 分页查询数据字典集合
     * @param queryParams
     * @return
     */
    @ApiOperation(value = "分页查询数据字典")
    @GetMapping
    public IPage<DictionaryVO> findPage(QueryParams<DictionaryDO> queryParams){
        IPage<DictionaryDTO> page = dictionaryService.findPage(queryParams.getPage(), queryParams.getQueryWrapper());
        return BeanCopierUtils.copy(page, DictionaryVO.class);
    }

    /**
     * 主键查询数据字典
     * @param   id  主键
     * @return  DictionaryResponse   数据字典领域对象
     */
    @ApiOperation(value = "主键查询数据字典")
    @GetMapping(value = "/{id}")
    public DictionaryVO findById(@PathVariable(value = "id") String id){
        DictionaryDTO dictionaryDTO = dictionaryService.findById(id);
        return BeanCopierUtils.copy(dictionaryDTO, DictionaryVO.class);
    }

    /**
     * 新增数据字典
     * @param dictionaryDTO
     * @return
     */
    @ApiOperation(value = "新增数据字典")
    @PostMapping
    public DictionaryVO create(@RequestBody @Validated DictionaryDTO dictionaryDTO) {
        dictionaryDTO = dictionaryService.createDictionary(dictionaryDTO);
        return BeanCopierUtils.copy(dictionaryDTO, DictionaryVO.class);
    }

    /**
     * 更新数据字典
     * @param id    主键
     * @param dictionaryDTO  更新对象参数
     * @return  DictionaryResponse   数据字典领域对象
     */
    @ApiOperation(value = "更新数据字典")
    @PutMapping(value = "/{id}")
    public boolean update(@PathVariable(value = "id") String id,
                              @RequestBody @Validated DictionaryDTO dictionaryDTO){
        if(StringUtils.isNotBlank(id)){
            dictionaryDTO.setId(id);
        }
        return dictionaryService.updateDictionary(dictionaryDTO);
    }

    /**
     *  删除数据字典
     * @param id    主键
     */
    @ApiOperation(value = "删除单个数据字典")
    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable(value = "id") String id){
        return dictionaryService.deleteDictionary(id);
    }

    /**
     *  删除数据字典
     * @param ids    查询参数对象
     */
    @ApiOperation(value = "批量删除数据字典")
    @DeleteMapping
    public boolean batchDelete(@RequestBody List<String> ids){
        AssertValid.notEmpty(ids, "数组参数不能为空");
        return dictionaryService.deleteDictionary(ids);
    }


}
