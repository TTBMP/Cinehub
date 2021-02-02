package com.ttbmp.cinehub.core.service.payment;

import com.ttbmp.cinehub.core.service.payment.request.PayServiceRequest;

/**
 * @author Palmieri Ivan
 */
public interface PaymentService {

    void pay(PayServiceRequest payServiceRequest) throws PaymentException;



}
