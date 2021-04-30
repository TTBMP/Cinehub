package com.ttbmp.cinehub.domain;

/**
 * @author Ivan Palmieri
 */
public class Seat {

    private int id;
    private long price;
    private boolean state;
    private String position;

    public Seat(int id, long price, boolean state, String position) {
        this.id = id;
        this.price = price;
        this.state = state;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
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
