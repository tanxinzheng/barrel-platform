package com.github.tanxinzheng.tools.process;

import cn.hutool.core.lang.UUID;
import com.github.tanxinzheng.tools.process.domain.ProcessVO;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class ProcessUtils {

    private static final String PROCESS_CACHE_KEY = "process:";

    private static final long default_handler_timeout = 4;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 根据子任务关键Key初始化ProcessVO对象
     * @param subTaskKeys
     * @return
     */
    public ProcessVO create(String eventTitle, List<String> subTaskKeys){
        String eventKey = UUID.fastUUID().toString();
        if(CollectionUtils.isEmpty(subTaskKeys)){
            return ProcessVO.builder()
                    .eventKey(eventKey)
                    .total(Maps.newHashMap())
                    .build();
        }
        Map<String, Integer> itemMaps = Maps.newHashMap();
        subTaskKeys.stream().forEach(item ->{
            itemMaps.put(item, 0);
        });
        ProcessVO processVO = ProcessVO.builder()
                .eventKey(eventKey)
                .total(itemMaps)
                .build();
        redisTemplate.opsForValue().set(PROCESS_CACHE_KEY + eventKey, processVO, default_handler_timeout, TimeUnit.HOURS);
        return processVO;
    }

    public ProcessVO create(List<String> subTaskKeys){
        return create(null, subTaskKeys);
    }

    /**
     * 完成某个子任务
     * @param subKey
     */
    public void doneSubTask(String eventKey, String subKey) {
        ProcessVO processVO = (ProcessVO) redisTemplate.opsForValue().get(PROCESS_CACHE_KEY + eventKey);
        processVO.getTotal().put(subKey, 1);
        redisTemplate.opsForValue().set(PROCESS_CACHE_KEY + eventKey, processVO, default_handler_timeout, TimeUnit.HOURS);
    }

    /**
     * 查询进度信息
     * @param eventKey
     * @return
     */
    public ProcessVO getProcessInfo(String eventKey){
        return (ProcessVO) redisTemplate.opsForValue().get(PROCESS_CACHE_KEY + eventKey);
    }

}
