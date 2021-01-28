package com.ttbmp.cinehub.service.email.mock;


import com.ttbmp.cinehub.core.service.email.EmailService;
import com.ttbmp.cinehub.core.service.email.EmailServiceRequest;

/**
 * @author Palmieri Ivan
 */
public class MockEmailService implements EmailService {

    @Override
    public boolean sendMail(EmailServiceRequest emailServiceRequest) {
        return true;
    }
}
