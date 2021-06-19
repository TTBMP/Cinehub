package com.ttbmp.cinehub.app.repository.customer;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.ticket.Ticket;

/**
 * @author Fabio Buracchi
 */
public interface CustomerRepository {

    Customer getCustomer(String customerId) throws RepositoryException;

    Customer getCustomer(Ticket ticket) throws RepositoryException;

}
