package com.ttbmp.cinehub.core.usecase.buyticket.response;

import com.ttbmp.cinehub.core.dto.TicketDto;

public class GetTicketBySeatsResponse {

    TicketDto ticketDto;

    public GetTicketBySeatsResponse(TicketDto ticketDto) {
        this.ticketDto = ticketDto;
    }

    public TicketDto getTicketDto() {
        return ticketDto;
    }

    public void setTicketDto(TicketDto ticketDto) {
        this.ticketDto = ticketDto;
    }
}
