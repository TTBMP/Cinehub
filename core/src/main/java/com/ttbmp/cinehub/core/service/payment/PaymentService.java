package com.ttbmp.cinehub.core.service.payment;

import com.ttbmp.cinehub.core.dto.TicketDto;
import com.ttbmp.cinehub.core.dto.UserDto;
import com.ttbmp.cinehub.core.entity.Payment;
import com.ttbmp.cinehub.core.service.payment.request.PayServiceRequest;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public interface PaymentService {

    /*Da integer ad un entity*/
    boolean pay(PayServiceRequest payServiceRequest);

    boolean refoundLastPayment(UserDto user, TicketDto ticket);

    boolean refoundById(UserDto user, String id);

    List<Payment> retriveListOfPayment(UserDto user);

    String getError();


}
