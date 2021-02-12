package com.ttbmp.cinehub.domain;

/**
 * @author Palmieri Ivan
 */
public class Seat {

    private int id;
    private Long price;


    public Seat(Long price,int id) {
        this.price = price;
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
