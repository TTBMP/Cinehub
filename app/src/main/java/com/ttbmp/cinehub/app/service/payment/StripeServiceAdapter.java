package com.ttbmp.cinehub.app.service.payment;

import com.ttbmp.cinehub.service.payment.stripe.StripeService;
import com.ttbmp.cinehub.service.payment.stripe.StripeServiceException;

/**
 * @author Fabio Buracchi
 */
public class StripeServiceAdapter implements PaymentService {

    private final StripeService stripeService = new StripeService();

    @Override
    public void pay(PayServiceRequest payServiceRequest) throws PaymentServiceException {
        try {
            stripeService.stripeService(
                    payServiceRequest.getEmail(),
                    payServiceRequest.getNameUser(),
                    payServiceRequest.getNumberOfCard(),
                    payServiceRequest.getTicketPrice(),
                    payServiceRequest.getCvv(),
                    payServiceRequest.getExpiredDate()
            );
        } catch (StripeServiceException exception) {
            throw new PaymentServiceException(exception.getMessage());
        }
    }

}
