package com.ttbmp.cinehub.core.entity;

public class Seat {
    //prezzo dentro ticket
    Long price;
    Boolean state;


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
}
