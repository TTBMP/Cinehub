package com.ttbmp.cinehub.core.entity.ticket.decorator;

import com.ttbmp.cinehub.core.entity.ticket.component.TicketAbstract;

public class TicketFoldingArmchair extends TicketDecorator {

    public TicketFoldingArmchair(TicketAbstract ticketAbstract) {
        super(ticketAbstract);
    }

    @Override
    public long operation() {
        return this.addPrice(super.operation());
    }

    private long addPrice(long price) {
        return price + 1;
    }

}
