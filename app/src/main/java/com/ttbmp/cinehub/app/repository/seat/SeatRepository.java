package com.ttbmp.cinehub.app.repository.seat;

import com.ttbmp.cinehub.app.repository.RepositoryException;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Seat;
import com.ttbmp.cinehub.domain.ticket.Ticket;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public interface SeatRepository {

    Seat getSeat(int id) throws RepositoryException;

    Seat getSeat(Ticket ticket) throws RepositoryException;

    List<Seat> getSeatList(Hall hall) throws RepositoryException;

}
