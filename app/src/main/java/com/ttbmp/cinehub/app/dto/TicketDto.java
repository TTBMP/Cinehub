package com.ttbmp.cinehub.app.dto;

import com.ttbmp.cinehub.domain.ticket.Ticket;
import lombok.Value;

/**
 * @author Ivan Palmieri
 */
@Value
public class TicketDto {

    int id;
    long price;
    SeatDto seatDto;

    public TicketDto(Ticket ticket) {
        this.id = ticket.getId();
        this.price = ticket.getPrice();
        this.seatDto = new SeatDto(ticket.getSeat(), true);
    }

}
