package com.ttbmp.cinehub.core.datamapper;

import com.ttbmp.cinehub.core.dto.HallDto;

import com.ttbmp.cinehub.core.entity.Hall;



public class HallDataMapper {

    private HallDataMapper() {
    }

    public static HallDto mapToDto(Hall hall) {

        HallDto hallDto = new HallDto(hall.getId());
        hallDto.setSeatList(SeatDataMapper.mapToDtoList(hall.getSeatList()));
        return  hallDto;
    }

    public static Hall mapToEntity(HallDto hallDto) {
        Hall hall = new Hall(hallDto.getId());
        hall.setSeatList(SeatDataMapper.mapToEntityList(hallDto.getSeatList()));
     return hall;
    }
}
