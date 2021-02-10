package com.ttbmp.cinehub.app.service.payment;

/**
 * @author Palmieri Ivan
 */
public class MockPaymentService implements PaymentService {

    @Override
    public void pay(PayServiceRequest payServiceRequest) {
        System.out.println("Payment successful!");
    }

}
