package com.github.tanxinzheng.module.account.access.controller;

import com.github.tanxinzheng.framework.core.model.SelectIndex;
import com.github.tanxinzheng.framework.core.model.SelectOptionModel;
import com.github.tanxinzheng.framework.core.model.SelectOptionQuery;
import com.github.tanxinzheng.framework.core.service.SelectService;
import com.github.tanxinzheng.framework.model.Result;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by tanxinzheng on 17/6/26.
 */
@Api(tags = {"系统配置"})
@Slf4j
@RestController
@RequestMapping(value = "/cache")
public class CacheController {

    @Autowired
    CacheManager cacheManager;

    Map<SelectIndex, SelectService> selectServiceMap = Maps.newHashMap();

//    @Autowired
//    public void register(List<SelectService> serviceList){
//        for (SelectService selectService : serviceList) {
//            selectServiceMap.put(selectService.getSelectIndex(), selectService);
//        }
//    }

    /**
     * 数据字典列表
     * @param   selectOptionQuery    数据字典查询参数对象
     * @return  List<SelectOptionModel> 数据字典领域分页对象
     */
    @ApiOperation(value = "查询数据字典列表")
    @GetMapping(value = "/select")
    public List<SelectOptionModel> getDictionaryList(@Valid SelectOptionQuery selectOptionQuery){
        String selectIndex = selectOptionQuery.getTypeCode();
        SelectIndex selectIndexKey = null;
        try{
            selectIndexKey = SelectIndex.valueOf(StringUtils.upperCase(selectIndex));
        }catch (IllegalArgumentException e){
            log.error(e.getMessage(), e);
        }
        if(selectIndexKey == null){
            selectIndexKey = SelectIndex.DICTIONARY;
        }
        SelectService defaultSelectService = selectServiceMap.get(selectIndexKey);
        if(defaultSelectService == null){
            return selectServiceMap.get(SelectIndex.DICTIONARY).selectOptionModels(selectOptionQuery);
        }
        return defaultSelectService.selectOptionModels(selectOptionQuery);
    }

    /**
     * 清除缓存
     * @param cacheKey
     * @return
     */
    @ApiOperation(value = "清除缓存")
    @DeleteMapping
    public Result clearCache(@RequestParam(value = "cacheKey") String cacheKey){
        cacheManager.getCache(cacheKey).clear();
        return Result.success("缓存清理成功");
    }
}
