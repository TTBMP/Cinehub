package com.ttbmp.cinehub.domain.ticket.decorator;

import com.ttbmp.cinehub.domain.ticket.Ticket;

/**
 * @author Palmieri Ivan
 */
public class TicketFoldingArmchair extends TicketDecorator {

    public TicketFoldingArmchair(Ticket ticket) {
        super(ticket);
    }

    @Override
    public long getPrice() {
        return this.addPrice(super.getPrice());
    }

    private long addPrice(long price) {
        return price + 1;
    }

}
