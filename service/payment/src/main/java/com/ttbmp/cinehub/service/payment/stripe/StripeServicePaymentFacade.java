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
            customer = serviceRequestStripe.getCustomer(payServiceRequest.getNameUser(), payServiceRequest.getNumberOfCard(), payServiceRequest.getEmail());
        } catch (StripeException e) {
            e.printStackTrace();
        }
        if (customer == null) {
            errorStripe = "Error while retrieving the customer";
            return false;
        }
        PaymentMethod paymentMethod = null;
        try {
            paymentMethod = serviceRequestStripe.getCard(customer);
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

    @Override
    public String getError() {
        return errorStripe;
    }


}
