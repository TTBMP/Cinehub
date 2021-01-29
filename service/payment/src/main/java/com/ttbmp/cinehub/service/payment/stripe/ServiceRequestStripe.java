package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.ttbmp.cinehub.core.entity.Payment;
import com.ttbmp.cinehub.core.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Palmieri Ivan
 */
public class ServiceRequestStripe {

    private static final String CUSTOMER = "customer";
    private static final String AMOUNT = "amount";
    private static boolean connected = false;

    public ServiceRequestStripe() {
        connection();
    }

    public static void connection() {
        if (!connected) {
            Stripe.apiKey = "sk_test_51HMsilCjyigVX8MsGwDmh3NZkTOsGj5D5ZQDkhepyBzB6nWCnejZAJskSlxP3WySswONDdN2CIC5aSbdmbIpfS0e00IgegI2Yz";
            connected = true;
        }
    }

    public Customer register(String email, String number, String name) throws StripeException {

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

    public Customer exist(User user) throws StripeException {
        CustomerCollection customers = retrieveListCustomer();
        if (customers == null) {
            return null;
        }
        for (int i = 0; i < customers.getData().size(); i++) {
            if (customers.getData().get(i).getEmail().compareTo(user.getEmail()) == 0) {
                return customers.getData().get(i);
            }
        }
        return null;

    }

    //Given an email retrieves the customer if it exists
    public Customer isExistent(String nome, String numberOfCard, String email) throws StripeException {
        CustomerCollection customers = retrieveListCustomer();
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

    public List<Payment> convertListOfPayment(PaymentIntentCollection paymentIntentCollection, ServiceRequestStripe serviceRequestStripe) {
        return serviceRequestStripe.getListOfPayment(paymentIntentCollection);
    }

    //Returns a list of users on Stripe
    public CustomerCollection retrieveListCustomer() throws StripeException {
        Map<String, Object> params = new HashMap<>();
        return Customer.list(params);
    }

    //Given a customer, the list of his cards returns
    public PaymentMethodCollection retrievePaymentMethod(Customer customer) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put(CUSTOMER, customer.getId());
        params.put("type", "card");
        return PaymentMethod.list(params);
    }

    //Given a customer, it retrieves the (0) card of the associated user
    public PaymentMethod retrieveCard(Customer customer) throws StripeException {
        PaymentMethodCollection paymentMethods = retrievePaymentMethod(customer);
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

    //This method returns a list of all payments made by that customer
    public PaymentIntentCollection retrieveListPaymentIntent(String nome, String numberOfCard, String email) throws StripeException {
        Customer customer = isExistent(nome, numberOfCard, email);
        Map<String, Object> params = new HashMap<>();
        params.put(CUSTOMER, customer.getId());
        return PaymentIntent.list(params);

    }

    public PaymentIntentCollection retrieveListPaymentIntent(Customer customer) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put(CUSTOMER, customer.getId());
        return PaymentIntent.list(params);

    }

    public PaymentIntentCollection updateListPaymentIntentById(PaymentIntentCollection paymentCollection, int id) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put(AMOUNT, paymentCollection.getData().get(id).getAmount());
        params.put("payment_intent", paymentCollection.getData().get(id).getId());
        Refund.create(params);
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("order_id", "6735");
        Map<String, Object> para = new HashMap<>();
        para.put("metadata", metadata);
        para.put("description", "refounded");
        paymentCollection.getData().get(id).update(para);
        return paymentCollection;

    }

    public PaymentIntent getPaymentIntent(String id) throws StripeException {
        PaymentIntent paymentIntent;
        paymentIntent = PaymentIntent.retrieve(id);
        Map<String, Object> params = new HashMap<>();
        params.put(AMOUNT, paymentIntent.getAmount());
        params.put("payment_intent", paymentIntent.getId());
        Refund.create(params);
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("order_id", "6735");
        Map<String, Object> para = new HashMap<>();
        para.put("metadata", metadata);
        para.put("description", "refounded");
        paymentIntent.update(para);
        return paymentIntent;
    }

    public List<Payment> getListOfPayment(PaymentIntentCollection paymentIntentCollection) {
        List<Payment> listOfPayment = new ArrayList<>();
        for (PaymentIntent intent : paymentIntentCollection.getData()) {
            Payment payment = new Payment(
                    intent.getId(),
                    intent.getAmount() / 100,
                    intent.getDescription()
            );
            listOfPayment.add(payment);
        }
        return listOfPayment;
    }


}
