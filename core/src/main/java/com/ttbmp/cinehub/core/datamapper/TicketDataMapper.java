package com.ttbmp.cinehub.core.datamapper;


import com.ttbmp.cinehub.core.dto.TicketDto;
import com.ttbmp.cinehub.core.entity.ticket.component.TicketAbstract;
import com.ttbmp.cinehub.core.entity.ticket.component.Ticket;

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

    public static TicketAbstract mapToEntity(TicketDto ticketDto) {
        TicketAbstract ticketAbstract = new Ticket(ticketDto.getPrice());
        ticketAbstract.setPosition(ticketDto.getPosition());
        return ticketAbstract;
    }


}
