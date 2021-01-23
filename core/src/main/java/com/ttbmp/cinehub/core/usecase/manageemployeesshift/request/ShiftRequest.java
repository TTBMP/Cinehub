package com.ttbmp.cinehub.core.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.usecase.Request;
import com.ttbmp.cinehub.core.utilities.notification.Notification;

/**
 * @author Massimo Mazzetti
 */

public class ShiftRequest extends Request {
    public static final Notification.Error MISSING_SHIFT = new Notification.Error("Shift non valido");

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
    public void validate() {
        if (shift == null) {
            notification.addError(MISSING_SHIFT);
        }
    }
}
