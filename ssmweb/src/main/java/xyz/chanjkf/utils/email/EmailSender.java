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

    public static void send(String toEmail, Long id, String content, String path, String userName) throws MessagingException {
        Session session = getSession();
        // Instantiate a message
        Message  msg = new MimeMessage(session);

        //Set message attributes
        msg.setFrom(new InternetAddress(FROM));
        InternetAddress[] address = {new InternetAddress(toEmail)};
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject("账号激活邮件");
        msg.setSentDate(new Date());
        path = path+"?validate=" + content + "&user_id=" + id;
        String message = "<h5>尊敬的"+userName+"您好</h5>"+
                "请您在24小时内点击下面的认证链接，完成账号邮箱认证。" +
                "<br/>" +
                "<a href="+"\""+path+"\""+" target="+path+">"+path+"</a>"+
//                "<a href='http://www.baidu.com/' target="+path+">"+path+"</a>"+
                "<br/>" +
                "过期后链接将会自动失效(如果无法点击该链接，可以将链接复制并粘帖到浏览器的地址输入框，然后单击回车即可)。"+
                "<br/>" +
                "如果您已经通过验证了，请忽略这封邮件。"+
                "<br/>" +
                "该邮件为系统自动发出，请勿回复！";

        msg.setContent( message, "text/html;charset=utf-8");

        //Send the message
        Transport.send(msg);
    }



}
