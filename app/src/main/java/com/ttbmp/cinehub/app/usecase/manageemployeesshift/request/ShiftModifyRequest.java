package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
public class ShiftModifyRequest extends AuthenticatedRequest {

    public static final Request.Error MISSING_SHIFT = new Request.Error("Shift not existing.");
    public static final Request.Error INVALID_SHIFT = new Request.Error("Invalid Shift.");
    public static final Request.Error MISSING_DATE = new Request.Error("Insert date.");
    public static final Request.Error INVALID_DATE = new Request.Error("Invalid date.");
    public static final Request.Error MISSING_START = new Request.Error("Invalid start.");
    public static final Request.Error MISSING_END = new Request.Error("Invalid end.");
    public static final Request.Error MISSING_HALL = new Request.Error("Invalid hall.");
    public static final Request.Error ERROR_TIME = new Request.Error("Invalid Schedule.");


    int shiftId;
    LocalDate date;
    LocalTime start;
    LocalTime end;
    int hallId;
    String type;

    public ShiftModifyRequest(String sessionToken, int shiftId, LocalDate date, LocalTime start, LocalTime end, int hallId, String type) {
        super(sessionToken);
        this.shiftId = shiftId;
        this.date = date;
        this.start = start;
        this.end = end;
        this.hallId = hallId;
        this.type = type;
    }

    @Override
    protected void onValidate() {
        if (shiftId == -1) {
            addError(MISSING_SHIFT);
        }
        if (date == null) {
            addError(MISSING_DATE);
        } else if (LocalDate.now().isAfter(date)) {
            addError(INVALID_DATE);
        }
        if (start == null) {
            addError(MISSING_START);
        } else if (end == null) {
            addError(MISSING_END);
        } else if (start.isAfter(end)) {
            addError(ERROR_TIME);
        }

    }


}
