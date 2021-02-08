package com.ttbmp.cinehub.domain.ticket.component;

/**
 * @author Palmieri Ivan
 */
public class Ticket extends TicketAbstract {

    private final long price;

    public Ticket(long price) {
        this.price = price;
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
