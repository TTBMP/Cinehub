package com.ttbmp.cinehub.core.datamapper;

import com.ttbmp.cinehub.core.dto.ProjectionDto;
import com.ttbmp.cinehub.core.entity.Projection;

/**
 * @author Palmieri Ivan
 */
public class ProjectionDataMapper {

    private ProjectionDataMapper() {
    }

    public static ProjectionDto mapToDto(Projection projection) {
        return new ProjectionDto(
                projection.getMovie(),
                projection.getCinema(),
                projection.getHall(),
                projection.getStartTime(),
                projection.getDate()
        );
    }

    public static Projection mapToEntity(ProjectionDto projectionDto) {
        return new Projection(
                projectionDto.getMovieDto(),
                projectionDto.getCinemaDto(),
                projectionDto.getHallDto(),
                projectionDto.getStartTime(),
                projectionDto.getDate()
        );
    }
}
