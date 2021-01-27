package com.github.tanxinzheng.module.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.tanxinzheng.framework.web.annotation.DictionaryTransfer;
import com.github.tanxinzheng.framework.web.dictionary.DictionaryInterpreterService;
import com.github.tanxinzheng.framework.web.dictionary.domain.DictInfoVO;
import com.github.tanxinzheng.module.system.dictionary.domain.entity.DictionaryDO;
import com.github.tanxinzheng.module.system.dictionary.service.DictionaryService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DictionaryInterpreter implements DictionaryInterpreterService {

    @Resource
    DictionaryService dictionaryService;

    /**
     * 翻译
     *
     * @param type 字典类型
     * @return
     */
    @Override
    public List<DictInfoVO> translate(String type) {
        LambdaQueryWrapper<DictionaryDO> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(DictionaryDO::getGroupCode, type);
        lambdaQueryWrapper.eq(DictionaryDO::getActive, Boolean.TRUE);
        lambdaQueryWrapper.eq(DictionaryDO::getIsShow, Boolean.TRUE);
        List<DictionaryDO> dictionaryDOList = dictionaryService.list(lambdaQueryWrapper);
        return Optional.ofNullable(dictionaryDOList).orElse(Lists.newArrayList()).stream().map(item -> {
            return DictInfoVO.builder()
                    .code(item.getDictionaryCode())
                    .desc(item.getDictionaryName())
                    .sort(item.getSort())
                    .build();
            }).collect(Collectors.toList());
    }

    /**
     * 服务类型
     *
     * @return
     */
    @Override
    public String getServiceType() {
        return DictionaryTransfer.DICT_SERVICE_TYPE;
    }
}
