package com.ttbmp.cinehub.app.dto;

/**
 * @author Palmieri Ivan
 */
public class TicketDto {

    private long price;
    private String position;

    public TicketDto(long price) {
        this.price = price;
    }


    public Long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
