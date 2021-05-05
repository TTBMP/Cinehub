package com.ttbmp.cinehub.app.usecase.buyticket.response;

import com.ttbmp.cinehub.app.dto.TicketDto;

/**
 * @author Ivan Palmieri
 */
public class TicketResponse {

    private final TicketDto ticketDto;

    public TicketResponse(TicketDto ticketDto) {
        this.ticketDto = ticketDto;
    }

    public TicketDto getTicketDto() {
        return ticketDto;
    }


}
