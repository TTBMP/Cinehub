package com.ttbmp.cinehub.app.repository.seat;

import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Seat;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public interface SeatRepository {

    List<Seat> getSeatList(Hall hall);

}
