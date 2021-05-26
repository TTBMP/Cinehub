package com.ttbmp.cinehub.app.dto;

import com.ttbmp.cinehub.domain.Seat;

/**
 * @author Ivan Palmieri
 */
public class SeatDto {

    private int id;
    private String position;
    private boolean isBooked;

    public SeatDto(Seat seat, boolean isBooked) {
        this.id = seat.getId();
        this.position = seat.getPosition();
        this.isBooked = isBooked;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

}
