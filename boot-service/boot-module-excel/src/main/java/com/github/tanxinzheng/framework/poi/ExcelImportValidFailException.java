package com.github.tanxinzheng.framework.poi;

import com.github.tanxinzheng.framework.exception.BusinessException;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;

/**
 * Created by tanxinzheng on 16/12/10.
 */
public class ExcelImportValidFailException extends BusinessException {

    private ExcelImportResult excelImportResult;

    /**
     * Creates a new BusinessException object.
     */
    public ExcelImportValidFailException(ExcelImportResult excelImportResult) {
        this.excelImportResult = excelImportResult;
    }

    public ExcelImportResult getExcelImportResult() {
        return excelImportResult;
    }

    public void setExcelImportResult(ExcelImportResult excelImportResult) {
        this.excelImportResult = excelImportResult;
    }
}
