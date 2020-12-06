package com.github.tanxinzheng.module.system.wopi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * @Description 文件预览接口
 * @Author tanxinzheng
 * @Date 2020/12/6
 */
@Slf4j
@Api(tags = "文件预览接口")
@RequestMapping(value = "/preview")
@Controller
public class PreviewController {

    private final static String wopi_server = "192.168.83.131";

    /**
     * 跳转至预览服务
     * @param fileKey
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "查询接口")
    @GetMapping(value = "/{fileKey}")
    public void redirectPreviewUrl(@PathVariable(value = "fileKey", required = true) String fileKey, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //IPage<Object> page = dictionaryService.findPage(queryParams.getPage(), queryParams.getQueryWrapper());
        String previewUrl = "http://{}/wv/wordviewerframe.aspx?WOPISrc=http://192.168.83.1/wopi/files/{}";
        previewUrl = String.format(previewUrl, wopi_server, fileKey);
        log.info("preview file key: {}, preview url: {}", fileKey, previewUrl);
        response.sendRedirect(previewUrl);
    }
}
