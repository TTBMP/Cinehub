package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.model.PaymentMethod;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CustomerHandler {

    //Given an email retrieves the customer if it exists
    public Customer getCustomer(String nome, String numberOfCard, String email,String expiredDate,String cvv) throws StripeException {
        var customers = getListCustomer();
        if (customers == null) {
            return null;
        }
        for (var i = 0; i < customers.getData().size(); i++) {
            if (customers.getData().get(i).getEmail().compareTo(email) == 0) {
                return customers.getData().get(i);
            }
        }
        return registerCustomer(email, numberOfCard, nome, LocalDate.parse(expiredDate),cvv);
    }

    //Returns a list of users on Stripe
    public CustomerCollection getListCustomer() throws StripeException {
        Map<String, Object> params = new HashMap<>();
        return Customer.list(params);
    }

    private Customer registerCustomer(String email, String number, String name, LocalDate expiredDate, String cvv) throws StripeException {
        Map<String, Object> card = new HashMap<>();
        card.put("number", number);
        card.put("exp_month", expiredDate.getMonthValue());
        card.put("exp_year", expiredDate.getYear());
        card.put("cvc", cvv);
        Map<String, Object> paramsCard = new HashMap<>();
        paramsCard.put("type", "card");
        paramsCard.put("card", card);
        PaymentMethod paymentMethod;
        paymentMethod= PaymentMethod.create(paramsCard);
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

    public Customer updateCreditCardCustomer(Customer customer, String numberOfCard, LocalDate expiredDate, String cvv) throws StripeException {
        Map<String, Object> card = new HashMap<>();
        card.put("number", numberOfCard);
        card.put("exp_month", expiredDate.getMonthValue());
        card.put("exp_year", expiredDate.getYear());
        card.put("cvc", cvv);
        Map<String, Object> paramsCard = new HashMap<>();
        paramsCard.put("type", "card");
        paramsCard.put("card", card);
        PaymentMethod paymentMethod;
        paymentMethod= PaymentMethod.create(paramsCard);
        Map<String, Object> paramUserCard = new HashMap<>();
        paramUserCard.put("customer", customer.getId());
        paymentMethod.attach(paramUserCard);
        return customer;
    }

}
