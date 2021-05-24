package com.ttbmp.cinehub.app.dto;

/**
 * @author Ivan Palmieri
 */
public class TicketDto {

    private int id;
    private long price;
    private SeatDto seatDto;

    public TicketDto(int id, long price, SeatDto seatDto) {
        this.id = id;
        this.price = price;
        this.seatDto = seatDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public SeatDto getSeatDto() {
        return seatDto;
    }

    public void setSeatDto(SeatDto seatDto) {
        this.seatDto = seatDto;
    }

}
