package com.ttbmp.cinehub.core.usecase.buyticket.request;

import com.ttbmp.cinehub.core.dto.ProjectionDto;
import com.ttbmp.cinehub.core.usecase.Request;

public class GetNumberOfSeatsRequest extends Request {

    public static final Request.Error MISSING_PROJECTION_ERROR = new Request.Error("Projection can't be null");
    private ProjectionDto projectionDto;


    public GetNumberOfSeatsRequest(ProjectionDto projectionDto) {
        this.projectionDto = projectionDto;

    }


    public ProjectionDto getProjectionDto() {
        return projectionDto;
    }

    public void setProjectionDto(ProjectionDto projectionDto) {
        this.projectionDto = projectionDto;
    }

    @Override
    public void onValidate() {

    }
}
