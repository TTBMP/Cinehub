package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.CinemaDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.domain.Cinema;

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
                cinemaDto.getCity(), cinemaDto.getAddress(),
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
