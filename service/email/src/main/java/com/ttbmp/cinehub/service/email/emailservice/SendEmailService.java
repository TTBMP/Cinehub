package com.ttbmp.cinehub.service.email.emailservice;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Ivan Palmieri
 */
public class SendEmailService { // TODO: fix this class FFS

    public void sendMail(String email) {
        final var username = "yourUsername@email.com";
        final var password = "password";
        var fromEmail = email;
        var toEmail = "toEmail@example.com";

        var properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.mail.yahoo.com");
        properties.put("mail.smtp.port", "587");

        var session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        //Start our mail message
        var msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject("Subject Line");

            Multipart emailContent = new MimeMultipart();

            //Text body part
            var textBodyPart = new MimeBodyPart();
            textBodyPart.setText("My multipart text");

            //Attachment body part.
            var pdfAttachment = new MimeBodyPart();
            pdfAttachment.attachFile("/home/parallels/Documents/docs/javamail.pdf");

            //Attach body parts
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(pdfAttachment);

            //Attach multipart to message
            msg.setContent(emailContent);
            Transport.send(msg);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

}



