package com.ttbmp.cinehub.app.repository.ticket;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.List;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
public interface TicketRepository {

    void saveTicket(Ticket ticket) throws RepositoryException;

    List<Ticket> getTicketList(Customer customer);

    List<Ticket> getTicketList(Projection projection) throws RepositoryException;

}
