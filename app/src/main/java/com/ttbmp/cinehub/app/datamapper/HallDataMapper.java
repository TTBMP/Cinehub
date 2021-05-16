package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.domain.Hall;

import java.util.List;

/**
 * @author Massimo Mazzetti, Ivan Palmieri
 */
public class HallDataMapper {

    private HallDataMapper() {
    }

    public static HallDto mapToDto(Hall hall) {
        return new HallDto(hall.getId(), SeatDataMapper.mapToDtoList(hall.getSeatList()), hall.getName());
    }

    public static Hall mapToEntity(HallDto hallDto) {
        if (hallDto != null) {
            return new Hall(hallDto.getId(), SeatDataMapper.mapToEntityList(hallDto.getSeatList()), hallDto.getName());
        }
        return null;
    }

    public static List<HallDto> mapToDtoList(List<Hall> hallList) {
        return DataMapperHelper.mapList(hallList, HallDataMapper::mapToDto);
    }

    public static List<Hall> mapToEntityList(List<HallDto> hallListDto) {
        return DataMapperHelper.mapList(hallListDto, HallDataMapper::mapToEntity);
    }

}
