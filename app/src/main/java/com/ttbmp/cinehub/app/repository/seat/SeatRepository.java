package com.ttbmp.cinehub.app.repository.seat;

import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Seat;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public interface SeatRepository {

    List<Seat> getSeatList(Hall hall);

    Seat getSeat(Ticket ticket);
}
