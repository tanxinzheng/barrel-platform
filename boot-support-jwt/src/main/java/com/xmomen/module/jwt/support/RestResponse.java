package com.xmomen.module.jwt.support;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by tanxinzheng on 2018/9/27.
 */
public class RestResponse<T> implements Serializable {

    private String code;
    private Integer status;
    private String message;
    private T data;
    private Long timestamp;
    private String requestId;

    public RestResponse() {
        this.timestamp = new Date().getTime();
    }

    public RestResponse(T data) {
        this.data = data;
        this.timestamp = new Date().getTime();
    }

    public static RestResponse ok(Object data) {
        RestResponse restResponse = new RestResponse(data);
        restResponse.setMessage("OK");
        restResponse.setStatus(HttpStatus.OK.value());
        restResponse.setCode(HttpStatus.OK.toString());
        return restResponse;
    }

    public static RestResponse error(String code, String message) {
        RestResponse restResponse = new RestResponse();
        restResponse.setMessage(message);
        restResponse.setCode(code);
        return restResponse;
    }

    public void toJSON(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);
        mapper.writeValue(out, this);
        out.flush();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
