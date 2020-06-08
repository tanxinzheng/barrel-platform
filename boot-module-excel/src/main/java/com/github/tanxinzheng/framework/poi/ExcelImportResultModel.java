package com.github.tanxinzheng.framework.poi;

import com.github.tanxinzheng.web.model.RestResponse;
import lombok.Data;

/**
 * Created by tanxinzheng on 16/12/10.
 */
@Data
public class ExcelImportResultModel extends RestResponse {

    public ExcelImportResultModel() {
        super();
    }

    /**
     * 校验失败的excel下载结果（可根据此下载的excel修改后重新导入）
     */
    private String validResultUrl;

}


