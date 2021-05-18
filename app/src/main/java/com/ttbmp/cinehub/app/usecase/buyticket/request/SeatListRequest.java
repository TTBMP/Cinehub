package com.ttbmp.cinehub.app.usecase.buyticket.request;

import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Ivan Palmieri
 */
public class SeatListRequest extends Request {

    private int projectionId;

    public SeatListRequest(int projectionId) {
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
