package com.ttbmp.cinehub.app.usecase.buyticket.response;

import com.ttbmp.cinehub.app.dto.ProjectionDto;

import java.util.ArrayList;
import java.util.List;

public class GetProjectionResponse {


    private final ProjectionDto projectionDto;
    private final List<String> projectionDtoTime = new ArrayList<>();

    public GetProjectionResponse(ProjectionDto projectionDto) {
        this.projectionDto = projectionDto;
        addProjectionDtoTime(projectionDto.getStartTime());
    }


    public ProjectionDto getProjectionDto() {
        return projectionDto;

    }

    public List<String> getProjectionDtoTime() {
        return projectionDtoTime;
    }


    public void addProjectionDtoTime(String projectionTime) {
        this.projectionDtoTime.add(projectionTime);
    }
}
