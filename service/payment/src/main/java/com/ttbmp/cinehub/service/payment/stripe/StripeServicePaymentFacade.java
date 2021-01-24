package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentIntentCollection;
import com.stripe.model.PaymentMethod;
import com.ttbmp.cinehub.core.entity.Payment;
import com.ttbmp.cinehub.core.entity.Ticket;
import com.ttbmp.cinehub.core.entity.User;
import com.ttbmp.cinehub.core.service.payment.PaymentService;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class StripeServicePaymentFacade implements PaymentService {

    private final ServiceRequestStripe serviceRequestStripe;

    public StripeServicePaymentFacade() {
        this.serviceRequestStripe = new ServiceRequestStripe();
    }

    @Override
    public boolean pay(User user, Ticket ticket) {
        /*Mettere la connessione qui*/
        Customer customer = serviceRequestStripe.isExistent(user);
        if (customer == null) {
            System.out.println("Errore durante il reperimento del customer");
            return false;
        }
        PaymentMethod paymentMethod = serviceRequestStripe.retriveCard(customer);
        if (paymentMethod == null) {
            System.out.println("Errore durante il reperimento della carta");
            return false;
        }
        if (serviceRequestStripe.updateBilance(customer, ticket.getPrice())) {
            System.out.println("Pagamento effettuato!");
            if (serviceRequestStripe.setBelance(customer, ticket.getPrice(), paymentMethod)) {
                System.out.println("Conto aggiornato correttamente!");
            } else {
                System.out.println("Problema nell'aggiornamento del conto!");
            }
        } else {
            System.out.println("Errore durante il pagamento");
            return false;
        }
        return true;

    }

    /*Rimborsa una specifica transazione mediante l'id*/
    @Override
    public boolean refoundById(User user, String id) {

        Customer customer = serviceRequestStripe.isExistent(user);
        PaymentIntent paymentIntent = serviceRequestStripe.getPaymentIntent(id);
        if (customer == null) {
            System.out.println("Impossibile rimborsare l'utente. Esso non esiste");
            return false;
        }
        if (paymentIntent == null) {
            System.out.println("Errore durante il recupero della transazione");
            return false;
        }
        if (serviceRequestStripe.updateBilance(customer, -(paymentIntent.getAmount().intValue() / 100))) {
            System.out.println("Rimorso effettuato");
            return true;
        }
        return false;


    }

    /*Rimborsa l'ultimo acquisto*/
    @Override
    public boolean refoundLastPayment(User user, Ticket ticket) {
        Customer customer = serviceRequestStripe.isExistent(user);
        if (customer == null) {
            System.out.println("Errore nel rimborso dell'utente");
            return false;
        }
        /*L'utente non deve registrarsi*/
        PaymentIntentCollection paymentCollection = serviceRequestStripe.retriveListPaymentIntent(customer);
        if (paymentCollection.getData().isEmpty()) {
            System.out.println("Errore nel reperire le transazioni dell'utente");
            return false;
        }
        paymentCollection = serviceRequestStripe.updateListPaymentIntentById(paymentCollection, 0);//id: indica l'elemento di romborso
        if (paymentCollection == null) {
            System.out.println("Errore aggiornamento stato biglietto");
            return false;
        }
        if (!serviceRequestStripe.updateBilance(customer, -(paymentCollection.getData().get(0).getAmount().intValue() / 100))) {
            System.out.println("Problemi con l'aggiornamento del conto");
            return false;
        }
        System.out.println("Rimorso effettuato");
        return true;

    }


    @Override
    public List<Payment> retriveListOfPayment(User user) {
        PaymentIntentCollection payment = serviceRequestStripe.retriveListPaymentIntent(user);
        return serviceRequestStripe.convertListOfPayment(payment, serviceRequestStripe);
    }


}
