package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.app.dto.ProjectionistDto;
import com.ttbmp.cinehub.app.usecase.Request;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateShiftRequest extends Request {

    public static final Request.Error MISSING_EMPLOYEE = new Request.Error("dipendente non valido");
    public static final Request.Error MISSING_DATE = new Request.Error("data non valida");
    public static final Request.Error MISSING_START = new Request.Error("inizio non valido");
    public static final Request.Error MISSING_END = new Request.Error("fine non valida");
    public static final Request.Error MISSING_HALL = new Request.Error("sala non valida");
    public static final Request.Error DATE_ERROR = new Request.Error("turno non valida");

    EmployeeDto employee;
    LocalDate date;
    LocalTime start;
    LocalTime end;
    HallDto hall;

    public CreateShiftRequest(EmployeeDto employee, LocalDate date, LocalTime start, LocalTime end) {
        this.employee = employee;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public CreateShiftRequest(EmployeeDto employee, LocalDate date, LocalTime start, LocalTime end, HallDto hall) {
        this.employee = employee;
        this.date = date;
        this.start = start;
        this.end = end;
        this.hall = hall;
    }

    public CreateShiftRequest() {

    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
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

    public HallDto getHall() {
        return hall;
    }

    public void setHall(HallDto hall) {
        this.hall = hall;
    }

    @Override
    protected void onValidate() {
        if (employee == null) {
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
        if(start.isAfter(end)){
            addError(DATE_ERROR);
        }
        if (hall == null && employee instanceof ProjectionistDto) {
            addError(MISSING_HALL);
        }
    }
}
