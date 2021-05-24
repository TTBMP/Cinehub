package com.ttbmp.cinehub.app.repository.seat;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.domain.Seat;

/**
 * @author Fabio Buracchi
 */
public class SeatProxy extends Seat {

    @SuppressWarnings("unused")
    public SeatProxy(ServiceLocator serviceLocator, int id, String position) {
        super(id, position);
    }

}
