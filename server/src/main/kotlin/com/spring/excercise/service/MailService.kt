package com.spring.excercise.service

import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.mail.*;
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


@Service
class MailService {
    val mailExecutor: ExecutorService = Executors.newFixedThreadPool(3)
    val EMAIL = "noreply.hoangcongcar.com@gmail.com"
    val MAIL_PASS = "lmqtmiipqehhbqhc"
    val SMTP_HOST_SERVER = "smtp.gmail.com"
    val SMTP_HOST_PORT = "587"
//    private static final ExecutorService mailExecutor = Executors.newFixedThreadPool(3);
//    private static final String ADMIN_EMAIL = “abcd@gmail.com";
//    private static final String ADMIN_PASSWORD = “123abcd”;
//    private static final String SMTP_HOST_SERVER = "smtp.gmail.com";
//    private static final String SMTP_HOST_PORT = "587";
//    private static Authenticator authenticator;
//    private static Properties props;
    private lateinit var session: Session

    init {
        val props = System.getProperties();
        props.put("mail.smtp.host", SMTP_HOST_SERVER);
        props.put("mail.smtp.port", SMTP_HOST_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        val authenticator = object: Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return  PasswordAuthentication(EMAIL, MAIL_PASS)
            }
        }
//        {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new ;
//            }
//        };
        session = Session.getInstance(props, authenticator);
    }
//    private String subject;
//    private String body;
//    private String receiveEmail;
//
//    public static MailFactory instance;
//
//    public static MailFactory getInstance() {
//        if (instance == null) {
//            instance = new MailFactory();
//        }
//        return instance;
//    }
//
//    public MailFactory() {
//        initComponent();
//    }
//
//    private void initComponent() {
//        props = System.getProperties();
//        props.put("mail.smtp.host", SMTP_HOST_SERVER);
//        props.put("mail.smtp.port", SMTP_HOST_PORT);
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        authenticator = new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(ADMIN_EMAIL, ADMIN_PASSWORD);
//            }
//        };
//        session = Session.getInstance(props, authenticator);
//    }
//
//    public void sendActiveMail(String otpCode) {
//        this.subject = “Register OTP Code";
//        this.body = "<p>Dear customer,</p>";
//        this.body += "<p>Please enter following code to active your account: <span style=\"\n" +
//                "    font-size: 15px;\n" +
//                "    font-weight: bold;\n" +
//                "\">" + otpCode + "</span>.</p>";
//        this.body += "<p>The code will expire 15 minutes after you receive this email.";
//        mailExecutor.execute(this);
//    }
//
//    public String getReceiveEmail() {
//        return receiveEmail;
//    }
//
//    public MailFactory setReceiveEmail(String toEmail) {
//        this.receiveEmail = toEmail;
//        return this;
//    }
//
//    public String getSubject() {
//        return subject;
//    }
//
//    public MailFactory setSubject(String subject) {
//        this.subject = subject;
//        return this;
//    }
//
//    public String getBody() {
//        return body;
//    }
//
//    public MailFactory setBody(String body) {
//        this.body = body;
//        return this;
//    }
//
//    @Override
//    public void run() {
//        try {
//            MimeMessage msg = new MimeMessage(session);
//            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
//            msg.addHeader("format", "flowed");
//            msg.addHeader("Content-Transfer-Encoding", "8bit");
//            msg.setFrom(new InternetAddress(ADMIN_EMAIL, MimaxConstant.APP_NAME));
//            msg.setReplyTo(InternetAddress.parse(ADMIN_EMAIL, false));
//            msg.setSubject(this.subject, "UTF-8");
//            msg.setContent(this.body, "text/html; charset=utf-8");
//            msg.setSentDate(new Date());
//            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.receiveEmail, false));
//            Transport.send(msg);
//            LOGGER.info("Active email was sent successfully!!");
//        } catch (MessagingException | UnsupportedEncodingException e) {
//            LOGGER.info("Send active email fail: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }

    fun sendVerifyMail(email: String?, token: String?) {
        try {
            val msg = MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(InternetAddress(EMAIL, "Exercise"))
            msg.replyTo = InternetAddress.parse(EMAIL, false)
            msg.setSubject("Excercise spring", "UTF-8");
            msg.setContent(token, "text/html; charset=utf-8");
            msg.sentDate = Date()
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            Transport.send(msg);
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}