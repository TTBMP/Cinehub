package com.ttbmp.cinehub.app.service.email;


/**
 * @author Ivan Palmieri
 */
public class MockEmailService implements EmailService {

    @Override
    public void sendMail(EmailServiceRequest emailServiceRequest) {
        emailServiceRequest.setEmail("Payment with success");
    }
}
