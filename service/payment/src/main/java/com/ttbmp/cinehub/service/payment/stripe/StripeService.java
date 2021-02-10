package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;

/**
 * @author Palmieri Ivan
 */
public class StripeService {

    private final StripeServiceRequest stripeServiceRequest;

    public StripeService() {
        this.stripeServiceRequest = new StripeServiceRequest();
    }

    public void pay(String email, String nameUser, String numberOfCard, long ticketPrice) throws StripeServiceException {
        Customer customer;
        PaymentMethod paymentMethod;
        try {
            customer = stripeServiceRequest.getCustomer(
                    nameUser,
                    numberOfCard,
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
