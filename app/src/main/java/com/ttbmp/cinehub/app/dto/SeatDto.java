package com.ttbmp.cinehub.app.dto;

import com.ttbmp.cinehub.domain.Seat;
import lombok.Value;

/**
 * @author Ivan Palmieri
 */
@Value
public class SeatDto {

    int id;
    String position;
    boolean isBooked;

    public SeatDto(Seat seat, boolean isBooked) {
        this.id = seat.getId();
        this.position = seat.getPosition();
        this.isBooked = isBooked;
    }

}
