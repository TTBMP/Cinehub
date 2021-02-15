package com.ttbmp.cinehub.domain.ticket.decorator;

import com.ttbmp.cinehub.domain.ticket.component.Ticket;

/**
 * @author Ivan Palmieri
 */
public abstract class TicketDecorator extends Ticket {

    protected Ticket ticket;

    protected TicketDecorator(Ticket ticket) {
        super(ticket.getId(),ticket.getPrice(),  ticket.getOwner(), ticket.getSeat());
        this.ticket = ticket;
    }

    @Override
    public long getPrice() {
        return ticket.getPrice();
    }

}
