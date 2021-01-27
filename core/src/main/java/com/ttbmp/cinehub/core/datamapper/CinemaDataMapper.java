package com.ttbmp.cinehub.core.datamapper;

import com.ttbmp.cinehub.core.dto.CinemaDto;
import com.ttbmp.cinehub.core.entity.Cinema;
import com.ttbmp.cinehub.core.utilities.DataMapperHelper;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class CinemaDataMapper {

    private CinemaDataMapper() {
    }

    public static CinemaDto mapToDto(Cinema cinema) {
        if(cinema == null){
            return null;
        }
        return new CinemaDto(cinema.getName(), cinema.getAddress(), cinema.getCity());
    }

    public static Cinema mapToEntity(CinemaDto cinemaDto) {
        if (cinemaDto == null) return null;
        return new Cinema(
                cinemaDto.getName(),
                cinemaDto.getAddress(),
                cinemaDto.getCity()
        );
    }

    public static List<CinemaDto> mapToDtoList(List<Cinema> cinemaList) {
        return DataMapperHelper.mapList(cinemaList, CinemaDataMapper::mapToDto);
    }


}
