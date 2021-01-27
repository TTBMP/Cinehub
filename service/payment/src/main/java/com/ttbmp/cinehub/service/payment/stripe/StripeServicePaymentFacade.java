package com.ttbmp.cinehub.service.payment.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.ttbmp.cinehub.core.service.payment.PaymentService;
import com.ttbmp.cinehub.core.service.payment.request.PayServiceRequest;

/**
 * @author Palmieri Ivan
 */
public class StripeServicePaymentFacade implements PaymentService {

    private final ServiceRequestStripe serviceRequestStripe;

    String errorStripe;

    public StripeServicePaymentFacade() {
        this.serviceRequestStripe = new ServiceRequestStripe();
    }

    @Override
    public boolean pay(PayServiceRequest payServiceRequest) {
        Customer customer = null;
        try {
            customer = serviceRequestStripe.isExistent(payServiceRequest.getUserDto());
        } catch (StripeException e) {
            e.printStackTrace();
        }
        if (customer == null) {
            errorStripe = "Error while retrieving the customer";
            return false;
        }
        PaymentMethod paymentMethod = null;
        try {
            paymentMethod = serviceRequestStripe.retrieveCard(customer);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        if (paymentMethod == null) {
            errorStripe = "Error while retrieving paper";
            return false;
        }
        try {
            if (serviceRequestStripe.updateBalance(customer, payServiceRequest.getTicketPrice())) {
                if (!serviceRequestStripe.setBalance(customer, payServiceRequest.getTicketPrice(), paymentMethod)) {
                    errorStripe = "Problem updating your account";
                }
            } else {
                errorStripe = "Error during payment, or insufficient money";
                return false;
            }
        } catch (StripeException e) {
            e.printStackTrace();
        }

        return true;

    }

    /*Refund a specific transaction using the id
    @Override
    public boolean refoundById(UserDto userDto, String id) {

        Customer customer = null;
        try {
            customer = serviceRequestStripe.isExistent(userDto);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        PaymentIntent paymentIntent = null;
        try {
            paymentIntent = serviceRequestStripe.getPaymentIntent(id);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        if (customer == null) {
            errorStripe = "Unable to refund user. It doesn't exist";
            return false;
        }
        if (paymentIntent == null) {
            errorStripe = "Error while retrieving the transaction";
            return false;
        }
        try {
            if (serviceRequestStripe.updateBalance(customer, -(paymentIntent.getAmount().intValue() / 100))) {
                errorStripe = "Refund made";
                return true;
            }
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return false;


    }*/

    /*Refund your last purchase
    @Override
    public boolean refoundLastPayment(UserDto userDto, TicketDto ticket) {
        Customer customer = null;
        try {
            customer = serviceRequestStripe.isExistent(userDto);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        if (customer == null) {
            errorStripe = "User refund error";
            return false;
        }
        PaymentIntentCollection paymentCollection = new PaymentIntentCollection();
        try {
            paymentCollection = serviceRequestStripe.retrieveListPaymentIntent(customer);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        if (paymentCollection.getData().isEmpty()) {
            errorStripe = "Error finding user transactions";
            return false;
        }
        try {
            paymentCollection = serviceRequestStripe.updateListPaymentIntentById(paymentCollection, 0);//id: indicates the refund item
        } catch (StripeException e) {
            e.printStackTrace();
        }
        if (paymentCollection == null) {
            errorStripe = "Ticket status update error";
            return false;
        }
        try {
            if (!serviceRequestStripe.updateBalance(customer, -(paymentCollection.getData().get(0).getAmount().intValue() / 100))) {
                errorStripe = "Problems updating the account";
                return false;
            }
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return true;

    }*/

    /* @Override
    public List<Payment> retriveListOfPayment(UserDto userDto) {
        PaymentIntentCollection payment = null;
        try {
            payment = serviceRequestStripe.retrieveListPaymentIntent(userDto);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return serviceRequestStripe.convertListOfPayment(payment, serviceRequestStripe);
    }*/

    @Override
    public String getError() {
        return errorStripe;
    }


}
