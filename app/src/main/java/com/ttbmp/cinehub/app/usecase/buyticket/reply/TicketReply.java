package com.ttbmp.cinehub.app.usecase.buyticket.reply;

import com.ttbmp.cinehub.app.dto.TicketDto;

/**
 * @author Ivan Palmieri
 */
public class TicketReply {

    private final TicketDto ticketDto;

    public TicketReply(TicketDto ticketDto) {
        this.ticketDto = ticketDto;
    }

    public TicketDto getTicketDto() {
        return ticketDto;
    }


}
