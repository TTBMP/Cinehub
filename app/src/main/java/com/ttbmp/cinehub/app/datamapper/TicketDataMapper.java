package com.ttbmp.cinehub.app.datamapper;


import com.ttbmp.cinehub.app.dto.TicketDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.domain.ticket.component.Ticket;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public class TicketDataMapper {

    private TicketDataMapper() {
    }

    public static TicketDto mapToDto(Ticket ticket) {
        return new TicketDto(ticket.getId(), ticket.getPrice(), UserDataMapper.mapToDto(ticket.getOwner()) , SeatDataMapper.mapToDto(ticket.getSeat()));
    }

    public static Ticket mapToEntity(TicketDto ticketDto) {
        return new Ticket(ticketDto.getId(), ticketDto.getPrice(),UserDataMapper.mapToEntity(ticketDto.getOwner()), SeatDataMapper.mapToEntity(ticketDto.getSeatDto()));
    }

    public static List<TicketDto> mapToDtoList(List<Ticket> ticketList) {
        return DataMapperHelper.mapList(ticketList, TicketDataMapper::mapToDto);
    }

    public static List<Ticket> mapToEntityList(List<TicketDto> ticketDto) {
        return DataMapperHelper.mapList(ticketDto, TicketDataMapper::mapToEntity);
    }

}
