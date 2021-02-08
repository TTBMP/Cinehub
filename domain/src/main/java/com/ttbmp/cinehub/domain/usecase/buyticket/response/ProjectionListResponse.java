package com.ttbmp.cinehub.domain.usecase.buyticket.response;

import com.ttbmp.cinehub.domain.dto.ProjectionDto;

import java.util.ArrayList;
import java.util.List;

public class ProjectionListResponse {


    private final List<ProjectionDto> projectionDto;
    private final List<String> projectionDtoTime = new ArrayList<>();

    public ProjectionListResponse(List<ProjectionDto> projectionDto) {
        this.projectionDto = projectionDto;
        for (ProjectionDto projection : projectionDto) {
            addProjectionDtoTime(projection.getStartTime());
        }

    }


    public List<ProjectionDto> getProjectionDto() {
        return projectionDto;

    }

    public List<String> getProjectionDtoTime() {
        return projectionDtoTime;
    }


    public void addProjectionDtoTime(String projectionTime) {
        this.projectionDtoTime.add(projectionTime);
    }
}
