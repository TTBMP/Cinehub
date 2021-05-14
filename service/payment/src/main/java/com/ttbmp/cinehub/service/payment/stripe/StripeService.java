package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;

/**
 * @author Ivan Palmieri
 */
public class StripeService {

    private static final String CARD_NUMBER = "4242424242424242";
    private final StripeServiceBilance stripeServiceBilance;
    private final StripeServiceCustomer stripeServiceCustomer;
    private final StripeServicePayment stripeServicePayment;
    private static boolean connected = false;


    public StripeService() {
        this.stripeServiceBilance = new StripeServiceBilance();
        this.stripeServiceCustomer = new StripeServiceCustomer();
        this.stripeServicePayment = new StripeServicePayment();
    }

    @SuppressWarnings("Unused")
    public void stripeService(String email, String nameUser, String numberOfCard, long ticketPrice, String cvv, String expiredDate) throws StripeServiceException {
        try {
            connected();
            var customer = stripeServiceCustomer.getCustomer(
                    nameUser,
                    CARD_NUMBER,
                    email
            );
            var paymentMethod = stripeServicePayment.getCard(customer);
            if (stripeServiceBilance.updateBalance(customer, ticketPrice)) {
                stripeServiceBilance.setBalance(customer, ticketPrice, paymentMethod);
            }
        } catch (StripeException exception) {
            throw new StripeServiceException(exception.getMessage());
        }
    }

    private void connected() {
        if (!connected) {
            Stripe.apiKey = "sk_test_51HMsilCjyigVX8MsGwDmh3NZkTOsGj5D5ZQDkhepyBzB6nWCnejZAJskSlxP3WySswONDdN2CIC5aSbdmbIpfS0e00IgegI2Yz";
            connected = true;
        }
    }

}
