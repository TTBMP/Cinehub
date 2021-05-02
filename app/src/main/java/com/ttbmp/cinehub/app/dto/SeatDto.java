package com.ttbmp.cinehub.app.dto;

/**
 * @author Ivan Palmieri
 */
public class SeatDto {

    private int id;
    private String position;

    public SeatDto(int id, String position) {
        this.id = id;
        this.position = position;
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

}
