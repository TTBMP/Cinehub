package com.ttbmp.cinehub.core.datamapper;

import com.ttbmp.cinehub.core.dto.SeatDto;
import com.ttbmp.cinehub.core.entity.Seat;
import com.ttbmp.cinehub.core.utilities.DataMapperHelper;

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
        return new Seat(seatDto.getPrice(), seatDto.getState()
        );
    }

    public static List<SeatDto> mapToDtoList(List<Seat> seatList) {
        return DataMapperHelper.mapList(seatList, SeatDataMapper::mapToDto);
    }

    public static List<Seat> mapToEntityList(List<SeatDto> ticketDtoList) {
        return DataMapperHelper.mapList(ticketDtoList, SeatDataMapper::mapToEntity);

    }
}
