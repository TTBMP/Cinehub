package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.model.PaymentMethod;

import java.util.HashMap;
import java.util.Map;

public class CustomerHandler {

    //Given an email retrieves the customer if it exists
    public Customer getCustomer(String nome, String numberOfCard, String email) throws StripeException {
        var customers = getListCustomer();
        if (customers == null) {
            return null;
        }
        for (var i = 0; i < customers.getData().size(); i++) {
            if (customers.getData().get(i).getEmail().compareTo(email) == 0) {
                return customers.getData().get(i);
            }
        }
        return registerCustomer(email, numberOfCard, nome);
    }

    //Returns a list of users on Stripe
    public CustomerCollection getListCustomer() throws StripeException {
        Map<String, Object> params = new HashMap<>();
        return Customer.list(params);
    }

    private Customer registerCustomer(String email, String number, String name) throws StripeException {
        Map<String, Object> card = new HashMap<>();
        card.put("number", number);
        card.put("exp_month", 11);
        card.put("exp_year", 2024);
        card.put("cvc", "314");
        Map<String, Object> paramsCard = new HashMap<>();
        paramsCard.put("type", "card");
        paramsCard.put("card", card);
        PaymentMethod paymentMethod;
        paymentMethod = PaymentMethod.create(paramsCard);
        Customer customer;
        Map<String, Object> paramsUser = new HashMap<>();
        paramsUser.put("email", email);
        paramsUser.put("name", name);
        paramsUser.put("balance", 5000);
        customer = Customer.create(paramsUser);
        Map<String, Object> paramUserCard = new HashMap<>();
        paramUserCard.put("customer", customer.getId());
        paymentMethod.attach(paramUserCard);
        return customer;

    }

}
