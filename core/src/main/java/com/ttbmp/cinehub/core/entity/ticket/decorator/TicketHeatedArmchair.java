package com.ttbmp.cinehub.core.entity.ticket.decorator;

import com.ttbmp.cinehub.core.entity.ticket.component.Ticket;
/**
 * @author Palmieri Ivan
 */
public class TicketHeatedArmchair extends TicketDecorator {

    public TicketHeatedArmchair(Ticket ticket) {
        super(ticket);
    }

    @Override
    public long operation() {
        return this.addPrice(super.operation());
    }

    private long addPrice(long price) {
        return price + 1;
    }
}
