package com.ttbmp.cinehub.core.entity.ticket.component;

public class Ticket extends TicketAbstract {

    private long price;

    @Override
    public long getPrice(){
        return this.price;
    }

    public Ticket(long price) {
        this.price = price;
    }

    @Override
    public long operation() {
        return this.price;
    }



}
