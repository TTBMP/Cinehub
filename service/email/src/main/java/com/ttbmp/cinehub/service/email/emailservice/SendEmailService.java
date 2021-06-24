package com.ttbmp.cinehub.service.email.emailservice;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Ivan Palmieri
 */
public class SendEmailService {

    public void sendMail(String recipient, String object) throws SendEmailServiceException {
        try {
            var properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            var session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("cinehub.torvergata@gmail.com", "Torvergata");
                }
            });
            var message = prepareMassage(session, recipient, object);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new SendEmailServiceException(e.getMessage());
        }
    }

    private Message prepareMassage( Session session,  String recipient,String object) throws MessagingException {
        var message = new MimeMessage(session);
        message.setFrom(new InternetAddress("cinehub.torvergata@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject("Confirmation email CineHub");
        message.setText(object);
        return message;
    }

}



