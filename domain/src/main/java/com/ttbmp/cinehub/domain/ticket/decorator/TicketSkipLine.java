package com.ttbmp.cinehub.domain.ticket.decorator;

import com.ttbmp.cinehub.domain.ticket.Ticket;

/**
 * @author Ivan Palmieri
 */
public class TicketSkipLine extends TicketDecorator {

    public TicketSkipLine(Ticket ticket) {
        super(ticket);
    }

    @Override
    public long getPrice() {
        return ticket.getPrice() + 1;
    }

}
