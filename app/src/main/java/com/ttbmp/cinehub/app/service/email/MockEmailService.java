package com.ttbmp.cinehub.app.service.email;


/**
 * @author Palmieri Ivan
 */
public class MockEmailService implements EmailService {

    @Override
    public void sendMail(EmailServiceRequest emailServiceRequest) {
        System.out.println("Email sent");
    }
}
