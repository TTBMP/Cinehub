package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;

/**
 * @author Ivan Palmieri
 */
public class StripeService {

    private final StripeServiceRequest stripeServiceRequest;

    public StripeService() {
        this.stripeServiceRequest = new StripeServiceRequest();
    }

    @SuppressWarnings("Unused")
    public void pay(String email, String nameUser, String numberOfCard, long ticketPrice) throws StripeServiceException {
        Customer customer;
        PaymentMethod paymentMethod;
        try {
            customer = stripeServiceRequest.getCustomer(
                    nameUser,
                    "4242424242424242",
                    email
            );
            paymentMethod = stripeServiceRequest.getCard(customer);
            if (stripeServiceRequest.updateBalance(customer, ticketPrice)) {
                stripeServiceRequest.setBalance(customer, ticketPrice, paymentMethod);
            }
        } catch (StripeException exception) {
            throw new StripeServiceException(exception.getMessage());
        }
    }

}
