package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShiftModifyRequest extends AuthenticatedRequest {

    public static final Request.Error MISSING_SHIFT = new Request.Error("Shift non esiste");
    public static final Request.Error INVALID_SHIFT = new Request.Error("Shift non valido");
    public static final Request.Error MISSING_DATE = new Request.Error("Data non inserita");
    public static final Request.Error INVALID_DATE = new Request.Error("Data non valido");
    public static final Request.Error MISSING_START = new Request.Error("Inizio non valido");
    public static final Request.Error MISSING_END = new Request.Error("Fine non valida");
    public static final Request.Error MISSING_HALL = new Request.Error("Hall non valida");
    public static final Request.Error MISSING_EMPLOYEE = new Request.Error("Dipendente non valido");
    public static final Request.Error ERROR_TIME = new Request.Error("Orario non Valido");

    private String employeeId;
    private int shiftId;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private int hallId;

    public ShiftModifyRequest(String sessionToken, String employeeId, int shiftId, LocalDate date, LocalTime start, LocalTime end, int hallId) {
        super(sessionToken);
        this.employeeId = employeeId;
        this.shiftId = shiftId;
        this.date = date;
        this.start = start;
        this.end = end;
        this.hallId = hallId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    @Override
    protected void onValidate() {
        if (employeeId == null) {
            addError(MISSING_EMPLOYEE);
        }
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
        if (employeeId == null) {
            addError(MISSING_EMPLOYEE);
        }

    }


}
