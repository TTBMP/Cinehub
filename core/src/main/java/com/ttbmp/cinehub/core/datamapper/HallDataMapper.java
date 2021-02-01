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
        return new HallDto(
                hall.getNum(),
                CinemaDataMapper.mapToDto(hall.getCinema())
        );
    }

    public static Hall matToEntity(HallDto hallDto) {
        if (hallDto != null) {
            return new Hall(
                    hallDto.getNum(),
                    CinemaDataMapper.matToEntity(hallDto.getCinema())
            );
        }
        return null;
    }

    public static List<HallDto> mapToDtoList(List<Hall> hallList) {
        return DataMapperHelper.mapList(hallList, HallDataMapper::mapToDto);
    }

    public static List<Hall> mapToEntityList(List<HallDto> hallListDto) {
        return DataMapperHelper.mapList(hallListDto, HallDataMapper::matToEntity);
    }
}
