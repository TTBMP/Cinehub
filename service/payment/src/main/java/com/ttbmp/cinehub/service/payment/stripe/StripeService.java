package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.exception.StripeException;

/**
 * @author Ivan Palmieri
 */
public class StripeService {

    private static final String CARD_NUMBER = "4242424242424242";
    private final StripeServiceRequest stripeServiceRequest;

    public StripeService() {
        this.stripeServiceRequest = new StripeServiceRequest();
    }

    @SuppressWarnings("Unused")
    public void pay(String email, String nameUser, String numberOfCard, long ticketPrice) throws StripeServiceException {
        try {
            var customer = stripeServiceRequest.getCustomer(
                    nameUser,
                    CARD_NUMBER,
                    email
            );
            var paymentMethod = stripeServiceRequest.getCard(customer);
            if (stripeServiceRequest.updateBalance(customer, ticketPrice)) {
                stripeServiceRequest.setBalance(customer, ticketPrice, paymentMethod);
            }
        } catch (StripeException exception) {
            throw new StripeServiceException(exception.getMessage());
        }
    }

}
