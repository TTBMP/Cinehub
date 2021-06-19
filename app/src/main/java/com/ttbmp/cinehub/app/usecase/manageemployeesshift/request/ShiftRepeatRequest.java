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
public class ShiftRepeatRequest extends AuthenticatedRequest {

    public static final Request.Error MISSING_EMPLOYEE = new Request.Error("Dipendente non valido");
    public static final Request.Error MISSING_START = new Request.Error("Data inizio non valido");
    public static final Request.Error MISSING_END = new Request.Error("Data fine non valida");
    public static final Request.Error MISSING_OPTION = new Request.Error("Opzione non valida");
    public static final Request.Error MISSING_START_SHIFT = new Request.Error("Ora inizio non valido");
    public static final Request.Error MISSING_END_SHIFT = new Request.Error("Ora fine non valida");
    public static final Request.Error PERIOD_ERROR = new Request.Error("Periodo non valido");

    LocalDate start;
    LocalDate end;
    String option;
    String employeeId;
    LocalTime startShift;
    LocalTime endShift;
    int hallId;

    public ShiftRepeatRequest(String sessionToken, LocalDate start, LocalDate end, String option, String employeeId, LocalTime startShift, LocalTime endShift, int hallId) {
        super(sessionToken);
        this.start = start;
        this.end = end;
        this.option = option;
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
        if (option == null) {
            addError(MISSING_OPTION);
        }
        if (start == null) {
            addError(MISSING_START);
        } else if (end == null) {
            addError(MISSING_END);
        } else if (LocalDate.now().isAfter(start)) {
            addError(MISSING_START);
        } else if (start.isAfter(end)) {
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

}
