package com.ttbmp.cinehub.app.service.payment;

/**
 * @author Palmieri Ivan
 */
public interface PaymentService {

    void pay(PayServiceRequest payServiceRequest) throws PaymentServiceException;


}
