package com.github.tanxinzheng.framework.poi.exception;

import com.github.tanxinzheng.framework.poi.ExcelImportResultModel;
import com.github.tanxinzheng.framework.poi.ExcelImportValidFailException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

/**
 * Created by Jeng on 15/11/29.
 */
@Slf4j
@Order(1)
@ControllerAdvice
public class PoiExceptionHandler {

    private static final Log logger = LogFactory.getLog(PoiExceptionHandler.class);

    /**
     * Excel导入校验失败
     * @param ex
     * @param request
     * @return
     * @throws IOException
     */
    @ExceptionHandler({ExcelImportValidFailException.class})
    public final ResponseEntity<ExcelImportResultModel> handleException(ExcelImportValidFailException ex, HttpServletRequest request) throws IOException {
        ExcelImportResultModel restError = (ExcelImportResultModel) ExcelImportResultModel.failed(HttpStatus.BAD_REQUEST, "导入Excel数据校验失败");
        HttpHeaders headers = new HttpHeaders();
        ExcelImportResult excelImportResult = ex.getExcelImportResult();
        Workbook workbook = excelImportResult.getWorkbook();
        if(workbook != null){
            String uuid = DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
            String fileName = uuid + "_校验结果.xls";
            String encoderFileName = URLEncoder.encode(fileName, "UTF-8");
            ServletContext servletContext = request.getServletContext();
            String savepath = servletContext.getRealPath("/WEB-INF/temps");
            File file = new File(savepath, fileName);
            FileOutputStream os = new FileOutputStream(file);
            workbook.write(os);
            os.flush();
            os.close();
            restError.setValidResultUrl("/download/temps?file=" + encoderFileName);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    file.deleteOnExit();
                }
            }, 20000);
        }
        return new ResponseEntity<ExcelImportResultModel>(restError, headers, HttpStatus.BAD_REQUEST);
    }

}
