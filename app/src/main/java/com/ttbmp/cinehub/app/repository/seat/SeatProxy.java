package com.ttbmp.cinehub.app.repository.seat;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.domain.Seat;
import lombok.EqualsAndHashCode;

/**
 * @author Fabio Buracchi
 */
@EqualsAndHashCode(callSuper = true)
public class SeatProxy extends Seat {

    @SuppressWarnings("unused")
    public SeatProxy(ServiceLocator serviceLocator, int id, String position) {
        super(id, position);
    }

}
