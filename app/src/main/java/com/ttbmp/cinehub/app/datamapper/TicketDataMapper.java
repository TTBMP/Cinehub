package com.ttbmp.cinehub.app.datamapper;


import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;
import com.ttbmp.cinehub.domain.ticket.component.TicketAbstract;

import java.util.List;

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

    public static List<TicketDto> mapToDtoList(List<Ticket> ticketList) {
        return DataMapperHelper.mapList(ticketList, TicketDataMapper::mapToDto);
    }

    public static List<Ticket> mapToEntityList(List<TicketDto> ticketDtos) {
        return DataMapperHelper.mapList(ticketDtos, TicketDataMapper::mapToEntity);
    }

}
