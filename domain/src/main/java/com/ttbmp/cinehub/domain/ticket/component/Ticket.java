package com.ttbmp.cinehub.domain.ticket.component;

import com.ttbmp.cinehub.domain.User;

/**
 * @author Palmieri Ivan
 */
public class Ticket extends TicketAbstract {

    private final long price;
    private int id;
    private User owner;

    public Ticket(long price) {
        this.price = price;
    }

    public Ticket(int id, long price, User owner) {
        this.id = id;
        this.price = price;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public long increasePrice() {
        return this.price;
    }

    @Override
    public long getPrice() {
        return this.price;
    }

}
