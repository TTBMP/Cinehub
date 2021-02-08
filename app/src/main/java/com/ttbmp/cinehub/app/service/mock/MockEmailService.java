package com.ttbmp.cinehub.app.service.mock;


import com.ttbmp.cinehub.app.service.email.EmailService;
import com.ttbmp.cinehub.app.service.email.EmailServiceRequest;

/**
 * @author Palmieri Ivan
 */
public class MockEmailService implements EmailService {

    @Override
    public void sendMail(EmailServiceRequest emailServiceRequest) {
        System.out.println("Email sent");
    }
}
