package com.ttbmp.cinehub.domain.ticket.decorator;

import com.ttbmp.cinehub.domain.ticket.Ticket;
import lombok.EqualsAndHashCode;

/**
 * @author Ivan Palmieri
 */
@EqualsAndHashCode(callSuper = true)
public abstract class TicketDecorator extends Ticket {

    protected final Ticket ticket;

    protected TicketDecorator(Ticket ticket) {
        super(ticket.getId(), ticket.getPrice(), ticket.getOwner(), ticket.getSeat(), ticket.getProjection());
        this.ticket = ticket;
    }

    @Override
    public long getPrice() {
        return ticket.getPrice();
    }

}
