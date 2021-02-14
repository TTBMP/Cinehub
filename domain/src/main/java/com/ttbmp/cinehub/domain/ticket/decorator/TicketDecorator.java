package com.ttbmp.cinehub.domain.ticket.decorator;

import com.ttbmp.cinehub.domain.ticket.component.Ticket;

/**
 * @author Palmieri Ivan
 */
public abstract class TicketDecorator extends Ticket {

    Ticket ticket;

    TicketDecorator(Ticket ticket) {
        super(ticket.getId(),ticket.getPrice(),  ticket.getOwner(), ticket.getSeat());
        this.ticket = ticket;
    }

    @Override
    public long increasePrice() {
        return ticket.increasePrice();
    }
}
