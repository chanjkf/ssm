package xyz.chanjkf.utils.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author yi
 * @date 2017/12/26
 */
public class EmailSender {
    public static final String HOST = "smtp.163.com";
    public static final String PROTOCOL = "smtp";
    public static final int PORT = 25;
    /**
     * 发件人的email
     */
    public static final String FROM = "chanjkf@163.com";
    /**
     * 发件人密码
     */
    public static final String PWD = "chanjkf7019";
    /**
     * 获取Session
     * @return
     */
    private static Session getSession() {
        Properties props = new Properties();
        /**
         * 设置服务器地址
         */
        props.put("mail.smtp.host", HOST);
        /**
         * 设置协议
         */
        props.put("mail.store.protocol" , PROTOCOL);
        /**
         * 设置端口
         */
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth" , true);

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }

        };
        Session session = Session.getDefaultInstance(props , authenticator);

        return session;
    }

    public static void send(String toEmail, Long id, String content) throws MessagingException {
        Session session = getSession();
        // Instantiate a message
        Message  msg = new MimeMessage(session);

        //Set message attributes
        msg.setFrom(new InternetAddress(FROM));
        InternetAddress[] address = {new InternetAddress(toEmail)};
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject("账号激活邮件");
        msg.setSentDate(new Date());

        String message = "<a href=\"http://10.84.1.235:8090/photo/register/activate?validate=" + content + "&user_id=" + id + "\">点击此处激活</a>";
        msg.setContent( message, "text/html;charset=utf-8");

        //Send the message
        Transport.send(msg);
    }



}
