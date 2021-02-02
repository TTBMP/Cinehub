package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Palmieri Ivan
 */
public class StripeServiceRequest {

    private static final String CUSTOMER = "customer";
    private static final String AMOUNT = "amount";
    private static boolean connected = false;

    public StripeServiceRequest() {
        connection();
    }

    public static void connection() {
        if (!connected) {
            Stripe.apiKey = "sk_test_51HMsilCjyigVX8MsGwDmh3NZkTOsGj5D5ZQDkhepyBzB6nWCnejZAJskSlxP3WySswONDdN2CIC5aSbdmbIpfS0e00IgegI2Yz";
            connected = true;
        }
    }

    private Customer register(String email, String number, String name) throws StripeException {
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
        paramUserCard.put(CUSTOMER, customer.getId());
        paymentMethod.attach(paramUserCard);
        return customer;

    }


    //Given an email retrieves the customer if it exists
    public Customer getCustomer(String nome, String numberOfCard, String email) throws StripeException {
        CustomerCollection customers = getListCustomer();
        if (customers == null) {
            return null;
        }
        for (int i = 0; i < customers.getData().size(); i++) {
            if (customers.getData().get(i).getEmail().compareTo(email) == 0) {
                return customers.getData().get(i);
            }
        }
        return register(email, numberOfCard, nome);
    }


    //Returns a list of users on Stripe
    public CustomerCollection getListCustomer() throws StripeException {
        Map<String, Object> params = new HashMap<>();
        return Customer.list(params);
    }

    //Given a customer, the list of his cards returns
    private PaymentMethodCollection getPaymentMethod(Customer customer) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put(CUSTOMER, customer.getId());
        params.put("type", "card");
        return PaymentMethod.list(params);
    }

    //Given a customer, it retrieves the (0) card of the associated user
    public PaymentMethod getCard(Customer customer) throws StripeException {
        PaymentMethodCollection paymentMethods = getPaymentMethod(customer);
        if (paymentMethods == null) {
            return null;
        }
        return paymentMethods.getData().get(0);

    }

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
    public boolean setBalance(Customer customer, long price, PaymentMethod paymentMethod) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        long s = price * 100;
        params.put(AMOUNT, s);
        params.put("currency", "eur");
        params.put(CUSTOMER, customer.getId());
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        Map<String, Object> param = new HashMap<>();
        param.put("payment_method", paymentMethod.getId());
        paymentIntent.confirm(param);
        return true;

    }



}
