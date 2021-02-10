package com.ttbmp.cinehub.domain;

/**
 * @author Palmieri Ivan
 */
public class Seat {

    private int id;
    private char rows;
    private int number;
    private Long price;
    private Boolean state;

    public Seat(Long price, Boolean state) {
        this.price = price;
        this.state = state;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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
