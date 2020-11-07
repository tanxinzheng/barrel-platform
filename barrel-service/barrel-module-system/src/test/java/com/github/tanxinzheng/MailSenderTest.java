package com.github.tanxinzheng;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Date 2020/8/23
 */
public class MailSenderTest extends AppTest {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String from;
    @Value("${spring.mail.nickname}")
    private String nickname;

    //简单邮件测试
    @Test
    public void sendSimple(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("email测试");
        message.setText("邮件测试内容");
        message.setFrom(from);
        message.setTo("tanxinzheng@139.com");
        javaMailSender.send(message);
    }
    //复杂邮件测试
    @Test
    public void sendComplicated() throws MessagingException, UnsupportedEncodingException {
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //用MimeMessageHelper来包装MimeMessage
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setSubject("email测试");
        mimeMessageHelper.setText("邮件测试内容");
        mimeMessageHelper.setTo("tanxinzheng@139.com");
        mimeMessageHelper.setFrom(from, nickname);
        mimeMessageHelper.addAttachment("fastdfs_client.conf",new File("E:\\xmomen-repo\\webapp\\barrel-parent\\barrel-service\\barrel-module-system\\src\\test\\resources\\fastdfs_client.conf"));
        javaMailSender.send(mimeMessage);
    }
}
