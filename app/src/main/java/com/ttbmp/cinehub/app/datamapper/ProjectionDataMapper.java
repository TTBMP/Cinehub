package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.domain.Projection;

import java.util.List;

/**
 * @author Palmieri Ivan
 */
public class ProjectionDataMapper {

    private ProjectionDataMapper() {
    }

    public static ProjectionDto mapToDto(Projection projection) {
        return new ProjectionDto(
                MovieDataMapper.mapToDto(projection.getMovie()),
                CinemaDataMapper.mapToDto(projection.getCinema()),
                HallDataMapper.mapToDto(projection.getHall()),
                projection.getStartTime(),
                projection.getDate(),
                TicketDataMapper.mapToDtoList(projection.getTicketList()),
                projection.getId(),
                projection.getProjectionist()
        );
    }

    public static Projection mapToEntity(ProjectionDto projectionDto) {
        return new Projection(
                projectionDto.getId(),
                projectionDto.getDate(),
                projectionDto.getStartTime(),
                MovieDataMapper.mapToEntity(projectionDto.getMovieDto()),
                HallDataMapper.mapToEntity(projectionDto.getHallDto()),
                CinemaDataMapper.mapToEntity(projectionDto.getCinemaDto()),
                projectionDto.getProjectionist(),
                TicketDataMapper.mapToEntityList(projectionDto.getListTicket())
        );
    }

    public static List<ProjectionDto> mapToDtoList(List<Projection> projectionList) {
        return DataMapperHelper.mapList(projectionList, ProjectionDataMapper::mapToDto);
    }

    public static List<Projection> mapToEntityList(List<ProjectionDto> projectionDtoList) {
        return DataMapperHelper.mapList(projectionDtoList, ProjectionDataMapper::mapToEntity);
    }

}
