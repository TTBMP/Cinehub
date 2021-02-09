package com.ttbmp.cinehub.domain.ticket.component;

/**
 * @author Palmieri Ivan
 */
public abstract class TicketAbstract {

    private long price;
    private String position;


    public abstract long increasePrice();

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

}
