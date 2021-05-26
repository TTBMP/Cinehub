package com.ttbmp.cinehub.app.dto;

import com.ttbmp.cinehub.domain.ticket.component.Ticket;

/**
 * @author Ivan Palmieri
 */
public class TicketDto {

    private int id;
    private long price;
    private SeatDto seatDto;

    public TicketDto(Ticket ticket) {
        this.id = ticket.getId();
        this.price = ticket.getPrice();
        this.seatDto = new SeatDto(ticket.getSeat(), true);
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
