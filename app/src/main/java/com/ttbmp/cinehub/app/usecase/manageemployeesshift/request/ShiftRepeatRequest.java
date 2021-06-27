package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(callSuper = false)
public class ShiftRepeatRequest extends AuthenticatedRequest {

    public static final Request.Error MISSING_EMPLOYEE = new Request.Error("Invalid Employee.");
    public static final Request.Error MISSING_START = new Request.Error("Invalid start date.");
    public static final Request.Error MISSING_END = new Request.Error("Invalid end date.");
    public static final Request.Error MISSING_OPTION = new Request.Error("Invalid option.");
    public static final Request.Error MISSING_START_SHIFT = new Request.Error("Invalid start time.");
    public static final Request.Error MISSING_END_SHIFT = new Request.Error("Invalid end time.");
    public static final Request.Error PERIOD_ERROR = new Request.Error("Invalid Schedule.");

    RepeatOption repeatOption;
    String employeeId;
    LocalTime startShift;
    LocalTime endShift;
    int hallId;

    public ShiftRepeatRequest(String sessionToken, RepeatOption repeatOption, String employeeId, LocalTime startShift, LocalTime endShift, int hallId) {
        super(sessionToken);
        this.repeatOption = repeatOption;
        this.employeeId = employeeId;
        this.startShift = startShift;
        this.endShift = endShift;
        this.hallId = hallId;
    }

    @Override
    public void onValidate() {
        if (employeeId == null) {
            addError(MISSING_EMPLOYEE);
        }
        if (repeatOption.option == null) {
            addError(MISSING_OPTION);
        }
        if (repeatOption.start == null) {
            addError(MISSING_START);
        } else if (repeatOption.end == null) {
            addError(MISSING_END);
        } else if (LocalDate.now().isAfter(repeatOption.start)) {
            addError(MISSING_START);
        } else if (repeatOption.start.isAfter(repeatOption.end)) {
            addError(PERIOD_ERROR);
        }
        if (startShift == null) {
            addError(MISSING_START_SHIFT);
        } else if (endShift == null) {
            addError(MISSING_END_SHIFT);
        } else if (startShift.isAfter(endShift)) {
            addError(PERIOD_ERROR);
        }
    }

    @Value
    public static class RepeatOption {
        LocalDate start;
        LocalDate end;
        String option;
    }

}

