package com.ttbmp.cinehub.app.dto;

/**
 * @author Ivan Palmieri
 */
public class TicketDto {

    private long price;
    private int id;
    private UserDto owner;
    private SeatDto seatDto;

    public TicketDto(int id, long price, UserDto owner, SeatDto seatDto) {
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

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

}
