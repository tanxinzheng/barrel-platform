package com.github.tanxinzheng.framework.web.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by tanxinzheng on 2018/9/27.
 */
@Data
public class RestResponse<T> implements Serializable {

    private PageInfo pageInfo;
    private String code;
    private String path;
    private String timestamp;
    private Integer status;
    private String message;
    private T data;
    private String error;

    public RestResponse() {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public RestResponse(T data) {
        this.data = data;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static RestResponse success(Object data) {
        RestResponse restResponse = new RestResponse(data);
        restResponse.setMessage("SUCCESS");
        restResponse.setStatus(HttpStatus.OK.value());
        restResponse.setCode(String.valueOf(HttpStatus.OK.value()));
        return restResponse;
    }

    public static RestResponse success(PageInfo pageInfo, List data) {
        RestResponse restResponse = RestResponse.success(data);
        restResponse.setPageInfo(pageInfo);
        return restResponse;
    }

    public static RestResponse failed(HttpStatus code, String message) {
        RestResponse restResponse = new RestResponse();
        restResponse.setMessage(message);
        restResponse.setStatus(code.value());
        restResponse.setCode(String.valueOf(code.value()));
        return restResponse;
    }

    public static RestResponse failed(HttpStatus code, Exception ex) {
        return RestResponse.failed(code, ex.getMessage());
    }

    public static RestResponse validateFailed(String message) {
        RestResponse restResponse = new RestResponse();
        restResponse.setMessage(message);
        restResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        restResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return restResponse;
    }

    public static RestResponse validateFailed() {
        return RestResponse.validateFailed("请求参数校验不通过，请检查参数是否正常");
    }

    public void toJSON(HttpServletRequest request, HttpServletResponse response) throws IOException {
        toJSON(request, response, HttpStatus.OK);
    }

    public void toJSON(HttpServletRequest request, HttpServletResponse response, HttpStatus httpStatus) throws IOException {
        setPath(request.getRequestURI());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(httpStatus.value());
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);
        mapper.writeValue(out, this);
        out.flush();
    }

}
