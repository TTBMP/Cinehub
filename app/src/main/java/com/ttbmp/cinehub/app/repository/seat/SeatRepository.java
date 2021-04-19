package com.ttbmp.cinehub.app.repository.seat;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Seat;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.service.persistence.utils.jdbc.exception.DaoMethodException;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public interface SeatRepository {

    List<Seat> getSeatList(Hall hall) throws RepositoryException;

    Seat getSeat(Ticket ticket) throws RepositoryException;
}
