package com.ttbmp.cinehub.domain.datamapper;

import com.ttbmp.cinehub.domain.dto.HallDto;
import com.ttbmp.cinehub.domain.entity.Hall;
import com.ttbmp.cinehub.domain.utilities.DataMapperHelper;

import java.util.List;

/**
 * @author Massimo Mazzetti, Ivan Palmieri
 */
public class HallDataMapper {

    private HallDataMapper() {
    }

    public static HallDto mapToDto(Hall hall) {
        return new HallDto(hall.getId(), SeatDataMapper.mapToDtoList(hall.getSeatList()));
    }

    public static Hall mapToEntity(HallDto hallDto) {
        if (hallDto != null) {
            return new Hall(hallDto.getId(), SeatDataMapper.mapToEntityList(hallDto.getSeatList()));
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
