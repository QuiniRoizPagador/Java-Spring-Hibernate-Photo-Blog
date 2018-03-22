package es.blog.utils;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Quini
 */
public class SendMail {

    /**
     *
     */
    public static Properties mailServerProperties;

    /**
     *
     */
    public static Session getMailSession;

    /**
     *
     */
    public static MimeMessage generateMailMessage;
    
    private static final String PASS = "your google password";

    /**
     *
     * @param title
     * @param content
     * @param from
     * @throws AddressException
     * @throws MessagingException
     */
    public static void send(String title, String content, String from)
            throws AddressException, MessagingException {

        Properties mailProps = new Properties();

        mailProps.put("mail.smtp.host", "smtp.gmail.com");
        mailProps.put("mail.smtp.port", "587");
        mailProps.put("mail.smtp.auth", "true");
        mailProps.put("mail.smtp.starttls.enable", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("yourGoogleUser", PASS);
            }
        };

        Session session = Session.getDefaultInstance(mailProps, authenticator);

        final MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));

        message.addRecipient(Message.RecipientType.TO, new InternetAddress("to your other google mail"));

        message.setSubject(title);

        message.setText(content);

        Transport.send(message);

    }

}
