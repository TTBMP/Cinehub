package com.ttbmp.cinehub.domain.ticket.decorator;

import com.ttbmp.cinehub.domain.ticket.component.TicketAbstract;

/**
 * @author Palmieri Ivan
 */
public abstract class TicketDecorator extends TicketAbstract {

    TicketAbstract ticketAbstract;

    TicketDecorator(TicketAbstract ticketAbstract) {
        this.ticketAbstract = ticketAbstract;
    }

    @Override
    public long increasePrice() {
        return ticketAbstract.increasePrice();
    }
}
