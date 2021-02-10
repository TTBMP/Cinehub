package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.datamapper.ProjectionDataMapper;
import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.domain.Projection;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class ProjectionListReply {

    private List<ProjectionDto> projectionDtoList;

    public ProjectionListReply(List<Projection> projectionList) {
        this.projectionDtoList = ProjectionDataMapper.mapToDtoList(projectionList);
    }

    public List<ProjectionDto> getProjectionDtoList() {
        return projectionDtoList;
    }

    public void setProjectionDtoList(List<ProjectionDto> projectionDtoList) {
        this.projectionDtoList = projectionDtoList;
    }
}
