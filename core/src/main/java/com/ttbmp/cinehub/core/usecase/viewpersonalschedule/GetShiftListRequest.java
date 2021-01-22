package com.ttbmp.cinehub.core.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.core.usecase.Request;
import com.ttbmp.cinehub.core.utilities.notification.Notification;

import java.time.LocalDate;

/**
 * @author Fabio Buracchi
 */
public class GetShiftListRequest extends Request {

    public static final Notification.Error MISSING_START_TIME_ERROR = new Notification.Error("Start time value can't be null");
    public static final Notification.Error MISSING_END_TIME_ERROR = new Notification.Error("End time value can't be null");
    public static final Notification.Error INVALID_TIME_SELECTION_ERROR = new Notification.Error("Start time value can't be after the end time");

    private LocalDate start;
    private LocalDate end;

    public GetShiftListRequest(LocalDate start, LocalDate end) {
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
    public void validate() {
        if (start == null) {
            notification.addError(MISSING_START_TIME_ERROR);
        }
        if (end == null) {
            notification.addError(MISSING_END_TIME_ERROR);
        }
        if (start != null && end != null) {
            if (start.isAfter(end)) {
                notification.addError(INVALID_TIME_SELECTION_ERROR);
            }
        }
    }

}
