package com.github.tanxinzheng.module.dictionary.controller;

import com.github.pagehelper.Page;
import com.github.tanxinzheng.framework.logger.ActionLog;
import com.github.tanxinzheng.framework.poi.ExcelUtils;
import com.github.tanxinzheng.framework.web.annotation.LoginUser;
import com.github.tanxinzheng.framework.web.model.CurrentLoginUser;
import com.github.tanxinzheng.framework.web.rest.ImportExcelResponse;
import com.github.tanxinzheng.module.dictionary.model.DictionaryModel;
import com.github.tanxinzheng.module.dictionary.model.DictionaryQuery;
import com.github.tanxinzheng.module.dictionary.service.DictionaryService;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-6-11 18:57:11
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/dictionary")
@Slf4j
public class DictionaryController {

    @Autowired
    DictionaryService dictionaryService;

    /**
     * 新增数据字典
     * @param   dictionaryModel  新增对象参数
     * @return  DictionaryModel   数据字典领域对象
     */
    @ApiOperation(value = "新增数据字典")
    @ActionLog(actionName = "新增数据字典")
    @PostMapping
    public DictionaryModel createDictionary(@LoginUser CurrentLoginUser loginUser, @RequestBody @Valid DictionaryModel dictionaryModel) {
        dictionaryModel.setCreatedUserId(loginUser.getId());
        dictionaryModel.setUpdatedUserId(loginUser.getId());
        return dictionaryService.createDictionary(dictionaryModel);
    }

    /**
     * 数据字典列表
     * @param   dictionaryQuery    数据字典查询参数对象
     * @return  Page<DictionaryModel> 数据字典领域分页对象
     */
    @ApiOperation(value = "查询数据字典列表")
    @ActionLog(actionName = "查询数据字典列表")
    @GetMapping
    public Page<DictionaryModel> getDictionaryList(@LoginUser CurrentLoginUser loginUser, DictionaryQuery dictionaryQuery){
        return dictionaryService.getDictionaryModelPage(dictionaryQuery);
    }

    /**
     * 查询单个数据字典
     * @param   id  主键
     * @return  DictionaryModel   数据字典领域对象
     */
    @ApiOperation(value = "查询数据字典")
    @ActionLog(actionName = "查询数据字典")
    @GetMapping(value = "/{id}")
    public DictionaryModel getDictionaryById(@PathVariable(value = "id") String id){
        return dictionaryService.getOneDictionaryModel(id);
    }

    /**
     * 更新数据字典
     * @param id    主键
     * @param dictionaryModel  更新对象参数
     * @return  DictionaryModel   数据字典领域对象
     */
    @ApiOperation(value = "更新数据字典")
    @ActionLog(actionName = "更新数据字典，字典编号：${DictionaryModel.dictionaryCode}")
    @PutMapping(value = "/{id}")
    public DictionaryModel updateDictionary(@PathVariable(value = "id") String id,
                           @LoginUser CurrentLoginUser loginUser,
                           @RequestBody @Valid DictionaryModel dictionaryModel){
        if(StringUtils.isNotBlank(id)){
            dictionaryModel.setId(id);
        }
        dictionaryModel.setCreatedUserId(loginUser.getId());
        dictionaryModel.setUpdatedUserId(loginUser.getId());
        dictionaryService.updateDictionary(dictionaryModel);
        return dictionaryService.getOneDictionaryModel(id);
    }

    /**
     *  删除数据字典
     * @param id    主键
     */
    @ApiOperation(value = "删除单个数据字典")
    @ActionLog(actionName = "删除单个数据字典")
    @DeleteMapping(value = "/{id}")
    public void deleteDictionary(@PathVariable(value = "id") String id){
        dictionaryService.deleteDictionary(id);
    }

    /**
     *  删除数据字典
     * @param dictionaryQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除数据字典")
    @ActionLog(actionName = "批量删除数据字典")
    @DeleteMapping
    public void deleteDictionaries(DictionaryQuery dictionaryQuery){
        dictionaryService.deleteDictionary(dictionaryQuery.getIds());
    }

    /**
     * 下载Excel模板
     */
    @ApiOperation(value = "下载数据字典导入模板")
    @ActionLog(actionName = "下载数据字典导入模板")
    @RequestMapping(value="/template", method = RequestMethod.GET)
    public void downloadTemplate(HttpServletRequest request,
                                 HttpServletResponse response) {
        List<DictionaryModel> dictionaryModelList = Lists.newArrayList();
        ExcelUtils.export(request, response, DictionaryModel.class, dictionaryModelList, "数据字典_模板");
    }

    /**
     * 导出Excel
     * @param dictionaryQuery
     * @param request
     * @param response
     */
    @ApiOperation(value = "导出数据字典")
    @ActionLog(actionName = "导出数据字典")
    @RequestMapping(value="/export", method = RequestMethod.GET)
    public void exportDictionaries(DictionaryQuery dictionaryQuery,
                                             HttpServletRequest request,
                                             HttpServletResponse response) {
        List<DictionaryModel> dictionaryModelList = dictionaryService.getDictionaryModelList(dictionaryQuery);
        ExcelUtils.export(request, response, DictionaryModel.class, dictionaryModelList, "数据字典");
    }

    /**
     * 导入Excel
     * @param file
     */
    @ApiOperation(value = "导入数据字典")
    @ActionLog(actionName = "导入数据字典")
    @RequestMapping(value="/import", method = RequestMethod.POST)
    public ImportExcelResponse importDictionaries(@RequestParam("file") MultipartFile file) {
        List<DictionaryModel> list = ExcelUtils.transform(file, DictionaryModel.class);
        if(CollectionUtils.isEmpty(list)){
            return ImportExcelResponse.fail();
        }
        dictionaryService.createDictionaries(list);
        return ImportExcelResponse.success(list.size());
    }

}
