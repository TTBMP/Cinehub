package com.ttbmp.cinehub.core.entity.ticket.decorator;

import com.ttbmp.cinehub.core.entity.ticket.component.TicketAbstract;

public class TicketDecorator extends TicketAbstract {

    TicketAbstract ticketAbstract;

    public TicketDecorator(TicketAbstract ticketAbstract) {
        this.ticketAbstract = ticketAbstract;
    }

    @Override
    public long operation() {
        return ticketAbstract.operation();
    }
}
