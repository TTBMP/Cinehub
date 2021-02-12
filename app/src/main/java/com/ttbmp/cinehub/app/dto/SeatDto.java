package com.ttbmp.cinehub.app.dto;

/**
 * @author Palmieri Ivan
 */
public class SeatDto {

    private Long price;
    private int id;

    public SeatDto(Long price, int state) {
        this.price = price;
        this.id = state;

    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int state) {
        this.id = state;
    }
}
