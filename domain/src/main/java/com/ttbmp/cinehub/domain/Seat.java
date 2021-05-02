package com.ttbmp.cinehub.domain;

/**
 * @author Ivan Palmieri
 */
public class Seat {

    private int id;
    private String position;

    public Seat(int id,   String position) {
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


    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        var other = (Seat) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
