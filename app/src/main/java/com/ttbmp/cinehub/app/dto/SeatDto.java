package com.ttbmp.cinehub.app.dto;

/**
 * @author Ivan Palmieri
 */
public class SeatDto {

    private int id;
    private String position;
    private boolean isBooked;

    public SeatDto(int id, String position, boolean isBooked) {
        this.id = id;
        this.position = position;
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
