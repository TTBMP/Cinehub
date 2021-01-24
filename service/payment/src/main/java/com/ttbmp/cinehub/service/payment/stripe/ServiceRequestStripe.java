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

    private static final  String CUSTOMER = "customer";
    private static final  String AMOUNT = "amount";
    private static boolean connected = false;

    public ServiceRequestStripe() {
        connection();
    }

    public static void connection() {
        if (!connected) {
            Stripe.apiKey = "sk_test_51HMsilCjyigVX8MsGwDmh3NZkTOsGj5D5ZQDkhepyBzB6nWCnejZAJskSlxP3WySswONDdN2CIC5aSbdmbIpfS0e00IgegI2Yz";
            System.out.println("Connessione riuscita!");
            connected = true;
        }
    }

    public Customer register(String email, String number, String name) {

        Map<String, Object> card = new HashMap<>();
        card.put("number", number);
        card.put("exp_month", 11);
        card.put("exp_year", 2024);
        card.put("cvc", "314");
        Map<String, Object> paramsCard = new HashMap<>();
        paramsCard.put("type", "card");
        paramsCard.put("card", card);
        PaymentMethod paymentMethod;
        try {
            paymentMethod = PaymentMethod.create(paramsCard);
        } catch (StripeException e) {
            System.out.println("Impossible creare il metodo di pagamento");
            return null;
        }
        Customer customer;
        Map<String, Object> paramsUser = new HashMap<>();
        paramsUser.put("email", email);
        paramsUser.put("name", name);
        paramsUser.put("balance", 5000);
        try {
            customer = Customer.create(paramsUser);

        } catch (StripeException e) {
            System.out.println("Impossible creare l'utente Stripe");
            return null;
        }
        Map<String, Object> paramUserCard = new HashMap<>();
        paramUserCard.put(CUSTOMER, customer.getId());
        try {
            paymentMethod.attach(paramUserCard);
        } catch (StripeException e) {
            System.out.println("Impossible associare la card all'utente Stripe");
            return null;
        }
        System.out.println("Utente creato! ->" + customer.getEmail());
        return customer;

    }

    public Customer exist(User user) {
        CustomerCollection customers = retriveListCustomer();
        if (customers == null) {
            return null;
        }
        for (int i = 0; i < customers.getData().size(); i++) {
            if (customers.getData().get(i).getEmail().compareTo(user.getEmail()) == 0) {
                //esiste
                System.out.println("Utente trovato! ->" + customers.getData().get(i).getEmail());
                return customers.getData().get(i);
            }
        }
        //Non esiste, in questo caso lo creo
        System.out.println("Utente non  trovato");
        return null;

    }

    /*Data un email recupera il customer se esiste*/
    public Customer isExistent(User user) {
        CustomerCollection customers = retriveListCustomer();
        if (customers == null) {
            System.out.println("Impossibile reperire la lista dei Customer da Stripe");
            return null;
        }
        for (int i = 0; i < customers.getData().size(); i++) {
            if (customers.getData().get(i).getEmail().compareTo(user.getEmail()) == 0) {
                System.out.println("Utente trovato! ->" + customers.getData().get(i).getEmail());
                return customers.getData().get(i);
            }
        }
        System.out.println("Utente non  trovato");
        return register(user.getEmail(), user.getCard().getNumber(), user.getName());
    }

    public List<Payment> convertListOfPayment(PaymentIntentCollection paymentIntentCollection, ServiceRequestStripe serviceRequestStripe) {
        return serviceRequestStripe.getListOfPayment(paymentIntentCollection);
    }

    /*Ritorna una lista di utenti presenti su Stripe*/
    public CustomerCollection retriveListCustomer() {
        Map<String, Object> params = new HashMap<>();
        CustomerCollection customers;
        try {
            customers = Customer.list(params);
        } catch (StripeException e) {
            System.out.println("Impossible recuperare la lista degli utenti");
            return null;
        }
        return customers;
    }

    /*Dato un customer ritorna la lista delle sue carte*/
    public PaymentMethodCollection retrivePaymentMethod(Customer customer) {
        Map<String, Object> params = new HashMap<>();
        params.put(CUSTOMER, customer.getId());
        params.put("type", "card");
        PaymentMethodCollection paymentMethods;
        try {
            paymentMethods = PaymentMethod.list(params);
        } catch (StripeException e) {
            return null;
        }
        return paymentMethods;
    }

    /*Dato un customer recupera la card(0) dell'utente associato*/
    public PaymentMethod retriveCard(Customer customer) {
        PaymentMethodCollection paymentMethods = retrivePaymentMethod(customer);
        if (paymentMethods == null) {
            System.out.println("Impossible recuperare le carte associate all'utente");
            return null;
        }
        return paymentMethods.getData().get(0);

    }

    /*Funzione per effettuare le transazioni su un customer*/
    public boolean updateBilance(Customer customer, long price) {
        if (customer.getBalance() < (price * 100)) {
            System.out.println("Soldi non sufficenti sul conto");
            return false;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("balance", customer.getBalance() - (price * 100));

        try {
            customer.update(params);
        } catch (StripeException e) {
            System.out.println("Impossible aggiornare il conto dell'utente Stripe");
            return false;
        }
        System.out.println("Da: " + (customer.getBalance() / 100) + "€");
        customer.setBalance(customer.getBalance() - (price * 100));
        System.out.println("A: " + (customer.getBalance() / 100) + "€");
        return true;

    }

    /*Funzione per aggiornare il conto di un customer*/
    public boolean setBelance(Customer customer, long price, PaymentMethod paymentMethod) {
        Map<String, Object> params = new HashMap<>();
        long s = price * 100;
        params.put(AMOUNT, s);
        params.put("currency", "eur");
        params.put(CUSTOMER, customer.getId());
        try {
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            Map<String, Object> param = new HashMap<>();
            param.put("payment_method", paymentMethod.getId());
            paymentIntent.confirm(param);

        } catch (StripeException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /*Questo meotodo ritorna una lista di tutti i pagamenti effettuati da quel customer*/
    public PaymentIntentCollection retriveListPaymentIntent(User user) {
        Customer customer = isExistent(user);
        Map<String, Object> params = new HashMap<>();
        params.put(CUSTOMER, customer.getId());
        PaymentIntentCollection paymentIntents = null;
        try {
            paymentIntents = PaymentIntent.list(params);
        } catch (StripeException e) {
            e.printStackTrace();
        }

        return paymentIntents;

    }

    public PaymentIntentCollection retriveListPaymentIntent(Customer customer) {
        Map<String, Object> params = new HashMap<>();
        params.put(CUSTOMER, customer.getId());
        PaymentIntentCollection paymentIntents = null;
        try {
            paymentIntents = PaymentIntent.list(params);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return paymentIntents;

    }

    public PaymentIntentCollection updateListPaymentIntentById(PaymentIntentCollection paymentCollection, int id) {
        System.out.println("Rimborso -> " + id);
        try {
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


        } catch (StripeException e) {
            System.out.println("Rimorso gia effettuato");
            return null;
        }
    }

    public PaymentIntent getPaymentIntent(String id) {
        PaymentIntent paymentIntent;

        try {
            paymentIntent = PaymentIntent.retrieve(id);
            System.out.println("Rimborso -> " + id);
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

        } catch (StripeException e) {
            System.out.println("Errore nel reperire l'intent del pagamento");
            return null;
        }
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
