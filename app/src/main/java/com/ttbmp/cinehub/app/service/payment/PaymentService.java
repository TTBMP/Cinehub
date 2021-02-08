package com.ttbmp.cinehub.app.service.payment;

import com.ttbmp.cinehub.app.service.payment.request.PayServiceRequest;

/**
 * @author Palmieri Ivan
 */
public interface PaymentService {

    void pay(PayServiceRequest payServiceRequest) throws PaymentException;


}
