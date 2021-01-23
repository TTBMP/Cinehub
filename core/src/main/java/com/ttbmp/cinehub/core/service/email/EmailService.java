package com.ttbmp.cinehub.core.service.email;

/**
 * @author Palmieri Ivan
 */
public interface EmailService {

    void sendMail(String to, String subject, String content);
}
