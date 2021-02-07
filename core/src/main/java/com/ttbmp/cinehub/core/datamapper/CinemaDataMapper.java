package com.ttbmp.cinehub.core.datamapper;

import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.utilities.DataMapperHelper;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public class CinemaDataMapper {

    private CinemaDataMapper() {
    }

    public static CinemaDto mapToDto(Cinema cinema) {

        return new CinemaDto(
                cinema.getId(),
                cinema.getName(),
                cinema.getAddress(),
                cinema.getCity(),
                HallDataMapper.mapToDtoList(cinema.getHallList())
        );
    }

    public static Cinema mapToEntity(CinemaDto cinemaDto) {

        return new Cinema(
                cinemaDto.getId(),
                cinemaDto.getName(),
                cinemaDto.getAddress(),
                cinemaDto.getCity(),
                HallDataMapper.mapToEntityList(cinemaDto.getHalList())
        );
    }

    public static List<CinemaDto> mapToDtoList(List<Cinema> cinemaList) {
        return DataMapperHelper.mapList(cinemaList, CinemaDataMapper::mapToDto);
    }

    public static List<Cinema> mapToEntityList(List<CinemaDto> cinemaListDto) {
        return DataMapperHelper.mapList(cinemaListDto, CinemaDataMapper::mapToEntity);
    }

}
