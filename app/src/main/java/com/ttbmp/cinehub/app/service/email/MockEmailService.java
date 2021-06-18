package com.ttbmp.cinehub.app.service.email;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ivan Palmieri
 */
public class MockEmailService implements EmailService {

    @Override
    public void sendMail(EmailServiceRequest emailServiceRequest) {
        var logger = Logger.getLogger(MockEmailService.class.getName());
        logger.log(Level.FINE, "Email sent");
    }

}
