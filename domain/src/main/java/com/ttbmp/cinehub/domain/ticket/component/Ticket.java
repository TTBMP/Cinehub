package com.ttbmp.cinehub.domain.ticket.component;

import com.ttbmp.cinehub.domain.Customer;
import com.ttbmp.cinehub.domain.Seat;

/**
 * @author Ivan Palmieri
 */
public class Ticket {

    private int id;
    private long price;
    private Seat seat;
    private Customer owner;

    public Ticket(int id, long price, Customer owner, Seat seat) {
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

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

}
