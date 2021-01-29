package com.ttbmp.cinehub.core.usecase.buyticket.response;

import com.ttbmp.cinehub.core.dto.ProjectionDto;

/**
 * @author Palmieri Ivan
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
