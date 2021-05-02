package com.ttbmp.cinehub.domain.ticket.decorator;

import com.ttbmp.cinehub.domain.ticket.component.Ticket;

/**
 * @author Ivan Palmieri
 */
public class TicketOpenBar extends TicketDecorator {

    public TicketOpenBar(Ticket ticket) {
        super(ticket);
    }

    @Override
    public long getPrice() {
        return ticket.getPrice() + 10;
    }

}
