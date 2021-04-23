package com.ttbmp.cinehub.app.repository.ticket;

import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.List;

/**
 * @author Fabio Buracchi, Ivan Palmieri
 */
public interface TicketRepository {

    void saveTicket(Ticket ticket, User user, Projection projection);

    List<Ticket> getTicketList(Projection projection);

}
