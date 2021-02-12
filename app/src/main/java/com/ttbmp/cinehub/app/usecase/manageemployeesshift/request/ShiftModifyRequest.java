package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.usecase.Request;

public class ShiftModifyRequest extends Request {
    public static final Request.Error MISSING_OLD_SHIFT = new Request.Error("Old Shift non valido");
    public static final Request.Error MISSING_NEW_SHIFT = new Request.Error("New Shift non valido");

    private ShiftDto oldShift;
    private ShiftDto newShift;

    public ShiftModifyRequest(ShiftDto oldShift, ShiftDto newShift) {
        this.oldShift = oldShift;
        this.newShift = newShift;
    }

    public ShiftDto getOldShift() {
        return oldShift;
    }

    public void setOldShift(ShiftDto oldShift) {
        this.oldShift = oldShift;
    }

    public ShiftDto getNewShift() {
        return newShift;
    }

    public void setNewShift(ShiftDto newShift) {
        this.newShift = newShift;
    }

    @Override
    protected void onValidate() {
        if (newShift == null) {
            addError(MISSING_NEW_SHIFT);
        }
        if (oldShift == null) {
            addError(MISSING_OLD_SHIFT);
        }

    }
}
