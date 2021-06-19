package com.ttbmp.cinehub.app.service.email;

import lombok.extern.java.Log;

/**
 * @author Ivan Palmieri
 */
@Log
public class MockEmailService implements EmailService {

    @Override
    public void sendMail(EmailServiceRequest emailServiceRequest) {
        log.info("Email sent");
    }

}
