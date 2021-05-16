package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;

/**
 * @author Ivan Palmieri
 */
public class StripeService {

    private static final String API_KEY = "sk_test_51HMsilCjyigVX8MsGwDmh3NZkTOsGj5D5ZQDkhepyBzB6nWCnejZAJskSlxP3WySswONDdN2CIC5aSbdmbIpfS0e00IgegI2Yz";
    private static final String CARD_NUMBER = "4242424242424242";

    private static final CustomerHandler CUSTOMER_HANDLER = new CustomerHandler();
    private static final PaymentMethodHandler PAYMENT_METHOD_HANDLER = new PaymentMethodHandler();
    private static final BalanceHandler BALANCE_HANDLER = new BalanceHandler();

    static {
        Stripe.apiKey = API_KEY;
    }

    @SuppressWarnings("Unused")
    public void requestPayment(String email, String nameUser, String numberOfCard, long ticketPrice) throws StripeServiceException {
        try {
            var customer = CUSTOMER_HANDLER.getCustomer(nameUser, CARD_NUMBER, email);
            var paymentMethod = PAYMENT_METHOD_HANDLER.getCreditCard(customer);
            if (BALANCE_HANDLER.updateBalance(customer, ticketPrice)) {
                BALANCE_HANDLER.setBalance(customer, ticketPrice, paymentMethod);
            }
        } catch (StripeException exception) {
            throw new StripeServiceException(exception.getMessage());
        }
    }

}
