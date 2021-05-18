package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.SeatDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.domain.Seat;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Ivan Palmieri
 */
public class SeatDataMapper {
    private SeatDataMapper() {
    }

    public static SeatDto mapToDto(Seat seat, boolean isBooked) {
        return new SeatDto(seat.getId(), seat.getPosition(), isBooked);
    }

    public static Seat mapToEntity(SeatDto seatDto) {
        return new Seat(seatDto.getId(), seatDto.getPosition());
    }

    public static List<SeatDto> mapToDtoList(List<Seat> seatList, Predicate<Seat> isBooked) {
        return DataMapperHelper.mapList(
                seatList,
                seat -> new SeatDto(
                        seat.getId(),
                        seat.getPosition(),
                        isBooked.test(seat)
                ));
    }

    public static List<Seat> mapToEntityList(List<SeatDto> ticketDtoList) {
        return DataMapperHelper.mapList(ticketDtoList, SeatDataMapper::mapToEntity);

    }
}
