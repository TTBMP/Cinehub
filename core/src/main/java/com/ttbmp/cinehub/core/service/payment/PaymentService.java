package com.ttbmp.cinehub.core.service.payment;
/**
 * @author Palmieri Ivan
 */

import com.ttbmp.cinehub.core.entity.Payment;
import com.ttbmp.cinehub.core.entity.Ticket;
import com.ttbmp.cinehub.core.entity.User;

import java.util.List;

public interface PaymentService {

    /*Da integer ad un entity*/
    boolean pay(User user, Ticket ticket);

    boolean refoundLastPayment(User user, Ticket ticket);

    boolean refoundById(User user, String id);

    List<Payment> retriveListOfPayment(User user);


}
