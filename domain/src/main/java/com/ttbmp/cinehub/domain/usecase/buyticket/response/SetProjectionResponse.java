package com.ttbmp.cinehub.domain.usecase.buyticket.response;

import com.ttbmp.cinehub.domain.dto.ProjectionDto;

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
