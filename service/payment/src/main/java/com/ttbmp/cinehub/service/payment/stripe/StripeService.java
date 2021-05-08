package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;

/**
 * @author Ivan Palmieri
 */
public class StripeService {

    private final StripeServiceRequest stripeServiceRequest;
    private static final String CARD_NUMBER = "4242424242424242";
    public StripeService() {
        this.stripeServiceRequest = new StripeServiceRequest();
    }

    @SuppressWarnings("Unused")
    public void pay(String email, String nameUser, String numberOfCard, long ticketPrice) throws StripeServiceException {
        try {
            Customer customer = stripeServiceRequest.getCustomer(
                    nameUser,
                    CARD_NUMBER,
                    email
            );
            PaymentMethod paymentMethod = stripeServiceRequest.getCard(customer);
            if (stripeServiceRequest.updateBalance(customer, ticketPrice)) {
                stripeServiceRequest.setBalance(customer, ticketPrice, paymentMethod);
            }
        } catch (StripeException exception) {
            throw new StripeServiceException(exception.getMessage());
        }
    }

}
