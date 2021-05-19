package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;

import java.time.LocalDate;

/**
 * @author Ivan Palmieri
 */
public class StripeService {

    private static final String API_KEY = "sk_test_51HMsilCjyigVX8MsGwDmh3NZkTOsGj5D5ZQDkhepyBzB6nWCnejZAJskSlxP3WySswONDdN2CIC5aSbdmbIpfS0e00IgegI2Yz";
    private static final CustomerHandler CUSTOMER_HANDLER = new CustomerHandler();
    private static final PaymentMethodHandler PAYMENT_METHOD_HANDLER = new PaymentMethodHandler();
    private static final BalanceHandler BALANCE_HANDLER = new BalanceHandler();


    static {
        Stripe.apiKey = API_KEY;
    }

    @SuppressWarnings("Unused")
    public void requestPayment(String email, String nameUser, String numberOfCard, long ticketPrice, String expiredDate, String cvv) throws StripeServiceException {
        try {
            var customer = CUSTOMER_HANDLER.getCustomer(nameUser, numberOfCard, email,expiredDate,cvv);
            var paymentMethod = PAYMENT_METHOD_HANDLER.getCreditCard(customer);
            if(!paymentMethod.getCard().getLast4().equals(numberOfCard.substring(numberOfCard.length()-4))){
                customer = CUSTOMER_HANDLER.updateCreditCardCustomer(customer,numberOfCard, LocalDate.parse(expiredDate) ,cvv);
                paymentMethod  = PAYMENT_METHOD_HANDLER.getCreditCard(customer);
            }
            BALANCE_HANDLER.checkAvailability(customer, ticketPrice);
            BALANCE_HANDLER.setBalance(customer, ticketPrice, paymentMethod);
        } catch (StripeException exception) {
            throw new StripeServiceException(exception.getStripeError().getMessage());
        }
    }

}
