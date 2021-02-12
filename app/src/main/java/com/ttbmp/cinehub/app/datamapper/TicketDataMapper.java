package com.ttbmp.cinehub.app.datamapper;


import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.domain.ticket.Ticket;

import java.util.List;

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
        Ticket ticket = new Ticket(ticketDto.getPrice());
        ticket.setPosition(ticketDto.getPosition());
        return ticket;
    }

    public static List<TicketDto> mapToDtoList(List<Ticket> ticketList) {
        return DataMapperHelper.mapList(ticketList, TicketDataMapper::mapToDto);
    }

    public static List<Ticket> mapToEntityList(List<TicketDto> ticketDtoList) {
        return DataMapperHelper.mapList(ticketDtoList, TicketDataMapper::mapToEntity);
    }


}
