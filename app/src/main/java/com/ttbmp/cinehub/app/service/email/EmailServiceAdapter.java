package com.ttbmp.cinehub.app.service.email;

import com.ttbmp.cinehub.service.email.emailservice.SendEmailService;

public class EmailServiceAdapter implements EmailService {
    private final SendEmailService service = new SendEmailService();

    @Override
    public void sendMail(EmailServiceRequest emailServiceRequest) {
        service.sendMail(emailServiceRequest.getEmail());
    }
}
