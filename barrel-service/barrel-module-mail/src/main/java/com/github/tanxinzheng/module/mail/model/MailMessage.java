package com.github.tanxinzheng.module.mail.model;

import javafx.util.Pair;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Date 2020/8/27
 */
@Data
@Builder
public class MailMessage implements Serializable {

    /**
     * 发件人邮箱
     */
    private String from;
    private String formPerson;
    @NotNull(message = "收件人不能为空")
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private Date sentDate;
    @NotBlank(message = "邮件标题不能为空")
    private String subject;
    @NotBlank(message = "邮件内容不能为空")
    private String text;
    /**
     * Pair<附件文件名称，附件文件流>
     */
    private List<Pair<String, InputStream>> attachments;
    private String templateContent;
    private Map<String, Object> templateParams;

}
