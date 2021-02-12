package com.ttbmp.cinehub.domain.ticket;

import com.ttbmp.cinehub.domain.User;

/**
 * @author Palmieri Ivan
 */
public class Ticket  {

    private long price;
    private User owner;
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Ticket(long price) {
        this.price = price;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


    public long getPrice() {
        return this.price;
    }




}
