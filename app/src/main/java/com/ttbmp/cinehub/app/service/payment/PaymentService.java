package com.ttbmp.cinehub.app.service.payment;

/**
 * @author Ivan Palmieri
 */
public interface PaymentService {

    void pay(PayServiceRequest payServiceRequest) throws PaymentServiceException;


}
