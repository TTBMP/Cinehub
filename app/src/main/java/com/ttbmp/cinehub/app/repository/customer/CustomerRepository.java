package com.ttbmp.cinehub.app.repository.customer;

import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

/**
 * @author Fabio Buracchi
 */
public interface CustomerRepository {

    Customer getCustomer(String userId);

    Customer getCustomer(Ticket ticket);

}
