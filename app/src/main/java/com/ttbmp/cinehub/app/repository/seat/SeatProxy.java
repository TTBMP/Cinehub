package com.ttbmp.cinehub.app.repository.seat;

import com.ttbmp.cinehub.domain.Seat;

/**
 * @author Fabio Buracchi
 */
public class SeatProxy extends Seat {

    public SeatProxy(int id, Long price, Boolean state) {
        super(id, price, state);
    }

}