package com.ttbmp.cinehub.app.dto;

/**
 * @author Palmieri Ivan
 */
public class SeatDto {

    private int id;
    private Long price;
    private Boolean state;
    private String position;

    public SeatDto(int id, Long price, Boolean state,String position) {
        this.id = id;
        this.price = price;
        this.state = state;
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
