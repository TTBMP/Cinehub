package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.utilities.request.Request;

/**
 * @author Massimo Mazzetti
 */
public class ShiftRequest extends Request {


    private int shiftId;

    public ShiftRequest(int shiftId) {
        this.shiftId = shiftId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    @Override
    protected void onValidate() {

    }

}
