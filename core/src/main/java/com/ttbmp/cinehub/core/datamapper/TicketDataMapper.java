package com.ttbmp.cinehub.core.datamapper;


import com.ttbmp.cinehub.core.dto.TicketDto;
import com.ttbmp.cinehub.core.entity.Ticket;

/**
 * @author Palmieri Ivan
 */
public class TicketDataMapper {

    private TicketDataMapper() {
    }

    public static TicketDto mapToDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto(ticket.getPrice());
        ticketDto.setPosition(ticket.getPosition());
        return ticketDto;
    }

    public static Ticket mapToEntity(TicketDto ticketDto) {
        return new Ticket(ticketDto.getPrice());
    }


}
