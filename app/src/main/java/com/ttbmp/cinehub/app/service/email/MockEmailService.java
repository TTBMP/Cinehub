package com.ttbmp.cinehub.app.service.email;


/**
 * @author Ivan Palmieri
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MockEmailService implements EmailService {

    @Override
    public void sendMail(EmailServiceRequest emailServiceRequest) {
        System.out.println("Email sent");
    }
}
