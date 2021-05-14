package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;

import java.util.HashMap;
import java.util.Map;

public class StripeServiceBilance {





    //Function to carry out transactions on a customer
    public boolean updateBalance(Customer customer, long price) throws StripeException {
        if (customer.getBalance() < (price * 100)) {
            return false;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("balance", customer.getBalance() - (price * 100));
        customer.update(params);
        customer.setBalance(customer.getBalance() - (price * 100));
        return true;

    }

    //Function to update a customer's account
    public void setBalance(Customer customer, long price, PaymentMethod paymentMethod) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        var s = price * 100;
        params.put("amount", s);
        params.put("currency", "eur");
        params.put("customer", customer.getId());
        var paymentIntent = PaymentIntent.create(params);
        Map<String, Object> param = new HashMap<>();
        param.put("payment_method", paymentMethod.getId());
        paymentIntent.confirm(param);
    }
}
