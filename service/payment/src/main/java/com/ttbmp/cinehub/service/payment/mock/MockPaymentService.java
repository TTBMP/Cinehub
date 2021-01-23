package com.ttbmp.cinehub.service.payment.mock;

import com.ttbmp.cinehub.core.entity.Payment;
import com.ttbmp.cinehub.core.entity.Ticket;
import com.ttbmp.cinehub.core.entity.User;
import com.ttbmp.cinehub.core.service.payment.PaymentService;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class MockPaymentService implements PaymentService {
    @Override
    public boolean pay(User user, Ticket ticket) {
        return true;
    }

    @Override
    public boolean refoundLastPayment(User user, Ticket ticket) {
        return true;
    }

    @Override
    public boolean refoundById(User user, String id) {
        return true;
    }

    @Override
    public List<Payment> retriveListOfPayment(User user) {
        return new ArrayList<>();
    }
}
