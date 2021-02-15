package com.ttbmp.cinehub.app.service.payment;

/**
 * @author Ivan Palmieri
 */
@SuppressWarnings({"common-java:DuplicatedBlocks", "java:S106"})
public class MockPaymentService implements PaymentService {

    @Override
    public void pay(PayServiceRequest payServiceRequest) {

        System.out.println("Payment successful!");
    }

}
