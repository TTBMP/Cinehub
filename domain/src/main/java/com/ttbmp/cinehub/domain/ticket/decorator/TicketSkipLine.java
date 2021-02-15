package com.ttbmp.cinehub.domain.ticket.decorator;

import com.ttbmp.cinehub.domain.ticket.component.Ticket;

/**
 * @author Ivan Palmieri
 */
public class TicketSkipLine extends TicketDecorator {


    public TicketSkipLine(Ticket ticket) {
        super(ticket);
    }

    @Override
    public long increasePrice() {
        return this.addPrice(super.increasePrice());
    }

    private long addPrice(long price) {
        return price + 1;
    }
}
