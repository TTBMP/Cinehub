package com.ttbmp.cinehub.app.dto;

import com.ttbmp.cinehub.domain.User;

/**
 * @author Ivan Palmieri
 */
public class TicketDto {

    private long price;
    private int id;
    private User owner;
    private SeatDto seatDto;

    public TicketDto(int id, long price, User owner, SeatDto seatDto) {
        this.id = id;
        this.owner = owner;
        this.price = price;
        this.seatDto = seatDto;
    }

    public SeatDto getSeatDto() {
        return seatDto;
    }

    public void setSeatDto(SeatDto seatDto) {
        this.seatDto = seatDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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


}
