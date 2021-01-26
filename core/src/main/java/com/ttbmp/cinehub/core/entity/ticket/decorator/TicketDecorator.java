package com.ttbmp.cinehub.core.entity.ticket.decorator;

import com.ttbmp.cinehub.core.entity.ticket.component.Ticket;
/**
 * @author Palmieri Ivan
 */
public class TicketDecorator extends Ticket {

    Ticket ticket;

    public TicketDecorator(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public long operation() {
        return ticket.operation();
    }
}
