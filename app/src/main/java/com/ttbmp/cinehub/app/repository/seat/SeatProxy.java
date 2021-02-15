package com.ttbmp.cinehub.app.repository.seat;

import com.ttbmp.cinehub.domain.Seat;

/**
 * @author Fabio Buracchi
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SeatProxy extends Seat {

    public SeatProxy(int id, Long price, Boolean state, String position) {
        super(id, price, state, position);
    }

}
