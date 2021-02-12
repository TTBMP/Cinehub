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
        ProjectionDto projectionDto = new ProjectionDto(
                MovieDataMapper.mapToDto(projection.getMovie()),
                CinemaDataMapper.mapToDto(projection.getCinema()),
                HallDataMapper.mapToDto(projection.getHall()),
                projection.getStartTime(),
                projection.getDate()
        );
        projectionDto.setTicketList(TicketDataMapper.mapToDtoList(projection.getTicketList()));
        return projectionDto;
    }

    public static Projection mapToEntity(ProjectionDto projectionDto) {
        Projection projection= new Projection(
                MovieDataMapper.mapToEntity(projectionDto.getMovieDto()),
                CinemaDataMapper.mapToEntity(projectionDto.getCinemaDto()),
                HallDataMapper.mapToEntity(projectionDto.getHallDto()),
                projectionDto.getStartTime(),
                projectionDto.getDate()
        );
        projection.setTicketList(TicketDataMapper.mapToEntityList(projectionDto.getTicketList()));
        return projection;
    }

    public static List<ProjectionDto> mapToDtoList(List<Projection> projectionList) {
        return DataMapperHelper.mapList(projectionList, ProjectionDataMapper::mapToDto);
    }

    public static List<Projection> mapToEntityList(List<ProjectionDto> projectionDtoList) {
        return DataMapperHelper.mapList(projectionDtoList, ProjectionDataMapper::mapToEntity);
    }
}
