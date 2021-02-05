package com.ttbmp.cinehub.core.datamapper;


import com.ttbmp.cinehub.core.dto.TicketDto;
import com.ttbmp.cinehub.core.entity.ticket.component.Ticket;
import com.ttbmp.cinehub.core.entity.ticket.component.TicketAbstract;

/**
 * @author Palmieri Ivan
 */
public class TicketDataMapper {

    private TicketDataMapper() {
    }

    public static TicketDto mapToDto(TicketAbstract ticketAbstract) {
        TicketDto ticketDto = new TicketDto(ticketAbstract.getPrice());
        ticketDto.setPosition(ticketAbstract.getPosition());
        return ticketDto;
    }

    public static Ticket mapToEntity(TicketDto ticketDto) {
        Ticket ticket = new Ticket(ticketDto.getPrice());
        ticket.setPosition(ticketDto.getPosition());
        return ticket;
    }


}
