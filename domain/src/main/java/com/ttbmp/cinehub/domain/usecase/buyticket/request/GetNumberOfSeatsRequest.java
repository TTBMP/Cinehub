package com.ttbmp.cinehub.domain.usecase.buyticket.request;

import com.ttbmp.cinehub.domain.dto.ProjectionDto;
import com.ttbmp.cinehub.domain.usecase.Request;

/**
 * @author Palmieri Ivan
 */
public class GetNumberOfSeatsRequest extends Request {

    public static final Request.Error MISSING_PROJECTION_ERROR = new Request.Error("Projection can't be null");

    private final ProjectionDto projectionDto;


    public GetNumberOfSeatsRequest(ProjectionDto projectionDto) {
        this.projectionDto = projectionDto;

    }


    public ProjectionDto getProjectionDto() {
        return projectionDto;
    }


    @Override
    public void onValidate() {
        if (projectionDto == null) {
            addError(MISSING_PROJECTION_ERROR);
        }
    }
}
