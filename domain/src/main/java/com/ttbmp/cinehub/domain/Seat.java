package com.ttbmp.cinehub.domain;

/**
 * @author Palmieri Ivan
 */
public class Seat {

    private int id;
    private long price;
    private boolean state;

    public Seat(int id, long price, boolean state) {
        this.id = id;
        this.price = price;
        this.state = state;
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
        Seat other = (Seat) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
