package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.domain.Projection;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class ProjectionListReply {

    private List<ProjectionDto> projectionDtoList;

    public ProjectionListReply(List<Projection> projectionList) {
        this.projectionDtoList = projectionList.stream()
                .map(ProjectionDto::new)
                .collect(Collectors.toList());
    }

    public List<ProjectionDto> getProjectionDtoList() {
        return projectionDtoList;
    }

    public void setProjectionDtoList(List<ProjectionDto> projectionDtoList) {
        this.projectionDtoList = projectionDtoList;
    }
}
