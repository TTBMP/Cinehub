package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.ttbmp.cinehub.domain.service.payment.PaymentException;
import com.ttbmp.cinehub.domain.service.payment.PaymentService;
import com.ttbmp.cinehub.domain.service.payment.request.PayServiceRequest;

/**
 * @author Palmieri Ivan
 */
public class StripeServicePayment implements PaymentService {

    private final StripeServiceRequest stripeServiceRequest;

    public StripeServicePayment() {
        this.stripeServiceRequest = new StripeServiceRequest();
    }

    @Override
    public void pay(PayServiceRequest payServiceRequest) throws PaymentException {
        Customer customer;
        PaymentMethod paymentMethod;
        try {
            customer = stripeServiceRequest.getCustomer(
                    payServiceRequest.getNameUser(),
                    payServiceRequest.getNumberOfCard(),
                    payServiceRequest.getEmail()
            );
            paymentMethod = stripeServiceRequest.getCard(customer);
            if (stripeServiceRequest.updateBalance(customer, payServiceRequest.getTicketPrice())) {
                if (!stripeServiceRequest.setBalance(customer, payServiceRequest.getTicketPrice(), paymentMethod)) {
                    throw new PaymentException("Problem updating your account");
                }
            } else {
                throw new PaymentException("Error during payment, or insufficient money");
            }
        } catch (StripeException e) {
            throw new PaymentException(e.getMessage());
        }
    }


}
