package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.dto.ProjectionDto;
import com.ttbmp.cinehub.app.usecase.Request;


/**
 * @author Ivan Palmieri
 */
public class CinemaInformationRequest extends Request {

    public static final Request.Error MISSING_PROJECTION_ERROR = new Request.Error("Projection can't be null");

    private ProjectionDto projectionDto;

    public CinemaInformationRequest(ProjectionDto projectionDto) {
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
        if (projectionDto == null) {
            addError(MISSING_PROJECTION_ERROR);
        }
    }
}
