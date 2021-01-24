package com.ttbmp.cinehub.service.payment.mock;

import com.ttbmp.cinehub.core.dto.TicketDto;
import com.ttbmp.cinehub.core.dto.UserDto;
import com.ttbmp.cinehub.core.entity.Payment;
import com.ttbmp.cinehub.core.service.payment.request.PayServiceRequest;
import com.ttbmp.cinehub.core.service.payment.PaymentService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockPaymentService implements PaymentService {
    @Override
    public boolean pay(PayServiceRequest payServiceRequest) {
        return true;
    }

    @Override
    public boolean refoundLastPayment(UserDto user, TicketDto ticket) {
        return true;
    }

    @Override
    public boolean refoundById(UserDto user, String id) {
        return true;
    }

    @Override
    public List<Payment> retriveListOfPayment(UserDto user) {
        return new ArrayList<>();
    }
}
