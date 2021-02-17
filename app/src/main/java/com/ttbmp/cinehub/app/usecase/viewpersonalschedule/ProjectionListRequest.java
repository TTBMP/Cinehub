package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Fabio Buracchi
 */
public class ProjectionListRequest extends Request {

    public static final Request.Error INVALID_SHIFT_ID_ERROR = new Request.Error("Shift id can't be negative");

    private int projectionistShiftId;

    public ProjectionListRequest(int projectionistShiftId) {
        this.projectionistShiftId = projectionistShiftId;
    }

    public int getProjectionistShiftId() {
        return projectionistShiftId;
    }

    public void setProjectionistShiftId(int projectionistShiftId) {
        this.projectionistShiftId = projectionistShiftId;
    }

    @Override
    protected void onValidate() {
        if (projectionistShiftId < 0) {
            addError(INVALID_SHIFT_ID_ERROR);
        }
    }

}
