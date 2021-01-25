package com.ttbmp.cinehub.core.entity.ticket.component;

public abstract class TicketAbstract {

    private TicketAbstract ticketAbstract;
    private long price;
    private Boolean state;
    private String position;


    public long operation() {
        return ticketAbstract.operation();
    }

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

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
