package com.ttbmp.cinehub.core.service.email.mock;


import com.ttbmp.cinehub.core.service.email.EmailService;
import com.ttbmp.cinehub.core.service.email.EmailServiceRequest;

/**
 * @author Palmieri Ivan
 */
public class MockEmailService implements EmailService {

    @Override
    public void sendMail(EmailServiceRequest emailServiceRequest) {
        System.out.println("Email sent");
    }
}
