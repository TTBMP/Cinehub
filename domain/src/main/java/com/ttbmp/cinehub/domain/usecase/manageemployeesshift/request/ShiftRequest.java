package com.ttbmp.cinehub.domain.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.domain.dto.ShiftDto;
import com.ttbmp.cinehub.domain.usecase.Request;

/**
 * @author Massimo Mazzetti
 */
public class ShiftRequest extends Request {

    public static final Request.Error MISSING_SHIFT = new Request.Error("Shift non valido");

    private ShiftDto shift;

    public ShiftRequest(ShiftDto shift) {
        this.shift = shift;
    }

    public ShiftDto getShift() {
        return shift;
    }

    public void setShift(ShiftDto shift) {
        this.shift = shift;
    }

    @Override
    protected void onValidate() {
        if (shift == null) {
            addError(MISSING_SHIFT);
        }
    }

}
