package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.model.PaymentMethodCollection;

import java.util.HashMap;
import java.util.Map;

public class PaymentMethodHandler {

    //Given a customer, it retrieves the (0) card of the associated user
    public PaymentMethod getCreditCard(Customer customer) throws StripeException {
        var paymentMethods = getPaymentMethod(customer);
        if (paymentMethods == null) {
            return null;
        }
        return paymentMethods.getData().get(0);

    }

    //Given a customer, the list of his cards returns
    private PaymentMethodCollection getPaymentMethod(Customer customer) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("customer", customer.getId());
        params.put("type", "card");
        return PaymentMethod.list(params);
    }

}
