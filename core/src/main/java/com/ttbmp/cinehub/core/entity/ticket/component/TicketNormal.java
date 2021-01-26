package com.ttbmp.cinehub.core.entity.ticket.component;
/**
 * @author Palmieri Ivan
 */
public class TicketNormal extends Ticket {

    private final long price;



    public TicketNormal(long price) {
        this.price = price;
    }

    @Override
    public long operation() {
        return this.price;
    }

    @Override
    public long getPrice(){
        return this.price;
    }



}
