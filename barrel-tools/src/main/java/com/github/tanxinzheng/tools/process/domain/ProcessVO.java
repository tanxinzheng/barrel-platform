package com.github.tanxinzheng.tools.process.domain;

import cn.hutool.core.util.NumberUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessVO {

    /**
     * 事件标题
     */
    private String eventTitle;
    /** 百分比 */
    private double percent;
    /** 事件key */
    private String eventKey;
    /**
     * 总任务数量
     */
    private Map<String, Integer> total;

    /**
     * 查询当前百分比
     * @return
     */
    public double getCurrentPercent(){
        if(CollectionUtils.isEmpty(total)){
            return 1;
        }
        long num = total.values().stream().filter(status -> {
            return status != null && status == 1;
        }).count();
        long totalNum = total.size();
        return NumberUtil.div(num, totalNum, 2);
    }

    public double getPercent() {
        return getCurrentPercent();
    }
}
