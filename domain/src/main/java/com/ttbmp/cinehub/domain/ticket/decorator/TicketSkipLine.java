package com.ttbmp.cinehub.domain.ticket.decorator;

import com.ttbmp.cinehub.domain.ticket.component.TicketAbstract;

/**
 * @author Palmieri Ivan
 */
public class TicketSkipLine extends TicketDecorator {


    public TicketSkipLine(TicketAbstract ticketAbstract) {
        super(ticketAbstract);
    }

    @Override
    public long increasePrice() {
        return this.addPrice(super.increasePrice());
    }

    private long addPrice(long price) {
        return price + 1;
    }
}
