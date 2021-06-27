package com.ttbmp.cinehub.app.service.email;

import com.ttbmp.cinehub.service.email.emailservice.SendEmailService;
import com.ttbmp.cinehub.service.email.emailservice.SendEmailServiceException;

public class EmailServiceAdapter implements EmailService {

    private final SendEmailService service = new SendEmailService();

    @Override
    public void sendMail(EmailServiceRequest request) throws EmailServiceException {
        try {
            service.sendMail(request.getEmail(), request.getObject());
        } catch (SendEmailServiceException e) {
            throw new EmailServiceException(e.getMessage());
        }
    }
}
