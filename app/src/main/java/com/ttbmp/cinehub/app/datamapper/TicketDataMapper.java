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
        return new TicketDto(
                ticket.getId(),
                ticket.getPrice(),
                SeatDataMapper.mapToDto(ticket.getSeat(), true)
        );
    }

    public static List<TicketDto> mapToDtoList(List<Ticket> ticketList) {
        return DataMapperHelper.mapList(ticketList, TicketDataMapper::mapToDto);
    }

}
