package com.ttbmp.cinehub.core.usecase.buyticket.response;

import com.ttbmp.cinehub.core.dto.ProjectionDto;

public class SetProjectionResponse {

    private ProjectionDto projectionDto;

    public SetProjectionResponse(ProjectionDto projectionDto) {
        this.projectionDto = projectionDto;
    }

    public ProjectionDto getProjectionDto() {
        return projectionDto;
    }

    public void setProjectionDto(ProjectionDto projectionDto) {
        this.projectionDto = projectionDto;
    }
}
