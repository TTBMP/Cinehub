package com.ttbmp.cinehub.core.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.usecase.Request;

/**
 * @author Massimo Mazzetti
 */

public class ShiftRequest extends Request {
    public static final Request.Error MISSING_SHIFT = new Request.Error("Shift non valido");

    private Shift shift;

    public ShiftRequest(Shift shift) {
        this.shift = shift;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    @Override
    protected void onValidate() {
        if (shift == null) {
            addError(MISSING_SHIFT);
        }
    }
}
