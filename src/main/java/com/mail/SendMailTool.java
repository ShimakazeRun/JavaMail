package com.mail;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SendMailTool {
    public static void main(String[] args) throws MessagingException,
            GeneralSecurityException {
        Properties props = new Properties();

        //开启debug调试
        props.setProperty("mail.debug", "true");
        //发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        //设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
        //发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.setProperty("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getInstance(props);

        Message message = new MimeMessage(session);
        message.setSubject("测试邮件");
        String builder = "晚上好" +
                "\n当前时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        message.setText(builder);
        message.setFrom(new InternetAddress("529033697@qq.com"));

        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com", "529033697@qq.com", "cigxzunyscykbjhj");

        transport.sendMessage(message, new Address[] {new InternetAddress("junyoplayfate@163.com")});
        transport.close();
    }
}
