package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(callSuper = false)
public class CreateShiftRequest extends AuthenticatedRequest {

    public static final Request.Error MISSING_EMPLOYEE = new Request.Error("Employee can't be null.");
    public static final Request.Error MISSING_DATE = new Request.Error("Date can't be null.");
    public static final Request.Error MISSING_START = new Request.Error("Start can't be null.");
    public static final Request.Error MISSING_END = new Request.Error("End can't be null.");
    public static final Request.Error MISSING_HALL = new Request.Error("Hall can't be null.");
    public static final Request.Error DATE_ERROR = new Request.Error("Invalid date.");
    public static final Request.Error TIME_ERROR = new Request.Error("Invalid time.");

    String employeeId;
    LocalDate date;
    LocalTime start;
    LocalTime end;
    int hallId;

    public CreateShiftRequest(String sessionToken, String employeeId, LocalDate date, LocalTime start, LocalTime end) {
        super(sessionToken);
        this.employeeId = employeeId;
        this.date = date;
        this.start = start;
        this.end = end;
        this.hallId = -1;
    }

    public CreateShiftRequest(String sessionToken, String employeeId, LocalDate date, LocalTime start, LocalTime end, int hallId) {
        super(sessionToken);
        this.employeeId = employeeId;
        this.date = date;
        this.start = start;
        this.end = end;
        this.hallId = hallId;
    }

    @Override
    protected void onValidate() {
        if (employeeId == null) {
            addError(MISSING_EMPLOYEE);
        }
        if (date == null) {
            addError(MISSING_DATE);
        } else if (LocalDate.now().isAfter(date)) {
            addError(DATE_ERROR);
        }
        if (start == null || end == null) {
            if (start == null) {
                addError(MISSING_START);
            }
            if (end == null) {
                addError(MISSING_END);
            }
        } else if (start.isAfter(end)) {
            addError(TIME_ERROR);
        }
    }

}
