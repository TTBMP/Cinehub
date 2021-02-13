package com.ttbmp.cinehub.app.dto;

/**
 * @author Palmieri Ivan
 */
public class SeatDto {

    private int id;
    private Long price;
    private Boolean state;

    public SeatDto(int id, Long price, Boolean state) {
        this.id = id;
        this.price = price;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
