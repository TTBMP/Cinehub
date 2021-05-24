package com.ttbmp.cinehub.app.datamapper;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.utilities.DataMapperHelper;
import com.ttbmp.cinehub.domain.Projection;

import java.util.List;

/**
 * @author Ivan Palmieri
 */
public class ProjectionDataMapper {

    private ProjectionDataMapper() {
    }

    public static ProjectionDto mapToDto(Projection projection) {
        return new ProjectionDto(
                projection.getId(),
                projection.getDate(),
                projection.getStartTime(),
                projection.getBasePrice(),
                MovieDataMapper.mapToDto(projection.getMovie()),
                HallDataMapper.mapToDto(projection.getHall())
        );
    }

    public static List<ProjectionDto> mapToDtoList(List<Projection> projectionList) {
        return DataMapperHelper.mapList(projectionList, ProjectionDataMapper::mapToDto);
    }

}
