package com.ttbmp.cinehub.app.usecase.buyticket.response;

import com.ttbmp.cinehub.app.dto.ProjectionDto;

import java.util.ArrayList;
import java.util.List;

public class GetProjectionListResponse {


    private final List<ProjectionDto> projectionDto;
    private final List<String> projectionDtoTime = new ArrayList<>();

    public GetProjectionListResponse(List<ProjectionDto> projectionDto) {
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
