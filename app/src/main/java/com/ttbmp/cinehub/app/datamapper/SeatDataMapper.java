package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.SeatDto;
import com.ttbmp.cinehub.domain.Seat;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class SeatDataMapper {
    private SeatDataMapper() {
    }

    public static SeatDto mapToDto(Seat seat) {
        return new SeatDto(seat.getPrice(), seat.getState());
    }

    public static Seat mapToEntity(SeatDto seatDto) {
        return new Seat(seatDto.getPrice(), seatDto.getState());
    }

    public static List<SeatDto> mapToDtoList(List<Seat> seatList) {
        return DataMapperHelper.mapList(seatList, SeatDataMapper::mapToDto);
    }

    public static List<Seat> mapToEntityList(List<SeatDto> ticketDtoList) {
        return DataMapperHelper.mapList(ticketDtoList, SeatDataMapper::mapToEntity);

    }
}
