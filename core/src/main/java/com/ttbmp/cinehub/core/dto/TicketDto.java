package com.ttbmp.cinehub.core.dto;


/**
 * @author Palmieri Ivan
 */
public class TicketDto {

    private Long price;
    private String position;


    public TicketDto(long price) {
        this.price = price;
    }

    public Long getPrice() {
        return price;
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
