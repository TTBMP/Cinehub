package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.usecase.Request;

import java.time.LocalDate;

/**
 * @author Fabio Buracchi
 */
public class ShiftListRequest extends Request {

    public static final Request.Error MISSING_START_TIME_ERROR = new Request.Error("Start time value can't be null");
    public static final Request.Error MISSING_END_TIME_ERROR = new Request.Error("End time value can't be null");
    public static final Request.Error INVALID_TIME_SELECTION_ERROR = new Request.Error("Start time value can't be after the end time");

    private LocalDate start;
    private LocalDate end;

    public ShiftListRequest(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public void onValidate() {
        if (start == null) {
            addError(MISSING_START_TIME_ERROR);
        }
        if (end == null) {
            addError(MISSING_END_TIME_ERROR);
        }
        if (start != null && end != null) {
            if (start.isAfter(end)) {
                addError(INVALID_TIME_SELECTION_ERROR);
            }
        }
    }

}
