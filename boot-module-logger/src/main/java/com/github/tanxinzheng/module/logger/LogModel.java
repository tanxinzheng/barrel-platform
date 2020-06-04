package com.github.tanxinzheng.module.logger;

import com.github.tanxinzheng.module.dictionary.web.AccountField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Jeng on 16/3/20.
 */
@Data
public class LogModel implements Serializable{


    @AccountField
    private String userId;
    private String actionName;
    private LocalDateTime actionDate;
    private String clientIp;
    private String targetClass;
    private String targetMethod;
    private String actionParams;
    private String actionResult;
    private String remark;

}
