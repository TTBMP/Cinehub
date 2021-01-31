package com.ttbmp.cinehub.core.usecase.buyticket.response;

import com.ttbmp.cinehub.core.dto.TicketDto;

/**
 * @author Palmieri Ivan
 */
public class GetTicketBySeatsResponse {

    private final TicketDto ticketDto;

    public GetTicketBySeatsResponse(TicketDto ticketDto) {
        this.ticketDto = ticketDto;
    }

    public TicketDto getTicketDto() {
        return ticketDto;
    }


}
