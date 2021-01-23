package com.ttbmp.cinehub.core.datamapper;

import com.ttbmp.cinehub.core.dto.HallDto;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.utilities.DataMapperHelper;

import java.util.List;

/**
 * @author Massimo Mazzetti
 */

public class HallDataMapper {

    private HallDataMapper() {
    }

    public static HallDto mapToDto(Hall hall) {
        HallDto hallDto = new HallDto(hall.getId());
        hallDto.setSeatList(SeatDataMapper.mapToDtoList(hall.getSeatList()));
        return hallDto;
    }

    public static Hall mapToEntity(HallDto hallDto) {
        Hall hall = new Hall(hallDto.getId());
        hall.setSeatList(SeatDataMapper.mapToEntityList(hallDto.getSeatList()));
        return hall;
    }

    public static List<HallDto> mapToDtoList(List<Hall> hallList) {
        return DataMapperHelper.mapList(hallList, HallDataMapper::mapToDto);
    }

    public static List<Hall> mapToEntityList(List<HallDto> hallListDto) {
        return DataMapperHelper.mapList(hallListDto, HallDataMapper::matToEntity);
    }

}
