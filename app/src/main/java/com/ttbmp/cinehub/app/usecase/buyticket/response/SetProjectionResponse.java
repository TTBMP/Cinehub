package com.ttbmp.cinehub.app.usecase.buyticket.response;

import com.ttbmp.cinehub.app.dto.ProjectionDto;

/**
 * @author Ivan Palmieri
 */
public class SetProjectionResponse {

    private final ProjectionDto projectionDto;

    public SetProjectionResponse(ProjectionDto projectionDto) {
        this.projectionDto = projectionDto;
    }

    public ProjectionDto getProjectionDto() {
        return projectionDto;
    }

}
