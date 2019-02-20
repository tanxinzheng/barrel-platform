package com.github.tanxinzheng.module.jwt.support;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by tanxinzheng on 2018/9/27.
 */
@Data
public class RestResponse<T> implements Serializable {

    private String code;
    private String path;
    private Long timestamp;
    private Integer status;
    private String message;
    private T data;
    private String error;

    public RestResponse() {
        this.timestamp = new Date().getTime();
    }

    public RestResponse(T data) {
        this.data = data;
        this.timestamp = new Date().getTime();
    }

    public static RestResponse ok(Object data) {
        RestResponse restResponse = new RestResponse(data);
        restResponse.setMessage("SUCCESS");
        restResponse.setStatus(HttpStatus.OK.value());
        return restResponse;
    }

    public static RestResponse error(HttpStatus code, String message) {
        RestResponse restResponse = new RestResponse();
        restResponse.setMessage(message);
        restResponse.setStatus(code.value());
        return restResponse;
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
