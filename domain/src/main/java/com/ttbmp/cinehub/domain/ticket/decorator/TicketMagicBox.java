package com.ttbmp.cinehub.domain.ticket.decorator;

import com.ttbmp.cinehub.domain.ticket.component.Ticket;

/**
 * @author Ivan Palmieri
 */
public class TicketMagicBox extends TicketDecorator {

    public TicketMagicBox(Ticket ticket) {
        super(ticket);
    }

    @Override
    public long getPrice() {
        return ticket.getPrice() + 15;
    }

}
