package com.ttbmp.cinehub.app.repository.ticket;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.List;

/**
 * @author Fabio Buracchi, Palmieri Ivan
 */
public interface TicketRepository {

    void saveTicket(Ticket ticket, int projectionId) throws  RepositoryException;

    List<Ticket> getTicketList(Projection projection) throws  RepositoryException;

}
