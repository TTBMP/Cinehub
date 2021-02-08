package com.ttbmp.cinehub.app.dto;

/**
 * @author Palmieri Ivan
 */
public class SeatDto {
    private Long price;
    private Boolean state;


    public SeatDto(Long price, Boolean state) {
        this.price = price;
        this.state = state;

    }


    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
