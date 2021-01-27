package com.ttbmp.cinehub.service.payment.mock;

import com.ttbmp.cinehub.core.service.payment.PaymentService;
import com.ttbmp.cinehub.core.service.payment.request.PayServiceRequest;

/**
 * @author Palmieri Ivan
 */
public class MockPaymentService implements PaymentService {
    @Override
    public boolean pay(PayServiceRequest payServiceRequest) {
        return true;
    }


    @Override
    public String getError() {
        return "";
    }

}
