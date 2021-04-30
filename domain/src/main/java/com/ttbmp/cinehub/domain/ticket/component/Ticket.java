package com.ttbmp.cinehub.domain.ticket.component;

import com.ttbmp.cinehub.domain.Seat;
import com.ttbmp.cinehub.domain.User;

/**
 * @author Ivan Palmieri
 */
public class Ticket {

    private int id;
    private long price;
    private Seat seat;
    private User owner;

    public Ticket(int id, long price, User owner, Seat seat) {
        this.id = id;
        this.price = price;
        this.owner = owner;
        this.seat = seat;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
