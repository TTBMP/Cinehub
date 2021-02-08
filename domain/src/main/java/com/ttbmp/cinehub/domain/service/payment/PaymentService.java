package com.ttbmp.cinehub.domain.service.payment;

import com.ttbmp.cinehub.domain.service.payment.request.PayServiceRequest;

/**
 * @author Palmieri Ivan
 */
public interface PaymentService {

    void pay(PayServiceRequest payServiceRequest) throws PaymentException;


}
