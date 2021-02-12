package com.ttbmp.cinehub.domain.ticket.decorator;

import com.ttbmp.cinehub.domain.ticket.Ticket;

/**
 * @author Palmieri Ivan
 */
public abstract class TicketDecorator extends Ticket {

    protected Ticket ticket;

    TicketDecorator(Ticket ticket) {
        super(ticket.getPrice());
        this.ticket = ticket;
    }

    @Override
    public long getPrice() {
        return ticket.getPrice();
    }
}
