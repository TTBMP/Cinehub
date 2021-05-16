package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.usecase.Request;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateShiftRequest extends Request {

    public static final Request.Error MISSING_EMPLOYEE = new Request.Error("dipendente non valido");
    public static final Request.Error MISSING_DATE = new Request.Error("data non valida");
    public static final Request.Error MISSING_START = new Request.Error("inizio non valido");
    public static final Request.Error MISSING_END = new Request.Error("fine non valida");
    public static final Request.Error DATE_ERROR = new Request.Error("ora non valida");

    private String employeeId;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private int hallId;

    public CreateShiftRequest(String employeeId, LocalDate date, LocalTime start, LocalTime end) {
        this.employeeId = employeeId;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public CreateShiftRequest(String employeeId, LocalDate date, LocalTime start, LocalTime end, int hallId) {
        this.employeeId = employeeId;
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
        if (date == null) {
            addError(MISSING_DATE);
        }
        if (start == null) {
            addError(MISSING_START);
        }
        if (end == null) {
            addError(MISSING_END);
        }
        if (start.isAfter(end)) {
            addError(DATE_ERROR);
        }
    }
}
