package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.Projection;

/**
 * @author Ivan Palmieri
 */
public class SeatListRequest extends AuthenticatedRequest {

    public static final Request.Error PROJECTION_ERROR = new Request.Error("Projection id can't be null");

    private int projectionId;

    public SeatListRequest(String sessionToken, int projectionId) {
        super(sessionToken);
        this.projectionId = projectionId;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    @Override
    public void onValidate() {
        // Do nothing because the class doesn't have attributes.
    }


}
