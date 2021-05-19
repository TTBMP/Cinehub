package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

import javax.naming.AuthenticationException;

/**
 * @author Ivan Palmieri
 */
public class SeatListRequest extends AuthenticatedRequest {

    private int projectionId;

    public SeatListRequest(String sessionToken,int projectionId) {
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

    }
}
