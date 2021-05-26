package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;
import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.employee.Projectionist;

import java.time.LocalDate;
import java.time.LocalTime;


public class ShiftRepeatRequest extends AuthenticatedRequest {
    public static final Request.Error MISSING_EMPLOYEE = new Request.Error("Dipendente non valido");
    public static final Request.Error MISSING_START = new Request.Error("Data inizio non valido");
    public static final Request.Error MISSING_END = new Request.Error("Data fine non valida");
    public static final Request.Error MISSING_OPTION = new Request.Error("Opzione non valida");
    public static final Request.Error MISSING_START_SHIFT = new Request.Error("Ora inizio non valido");
    public static final Request.Error MISSING_END_SHIFT = new Request.Error("Ora fine non valida");
    public static final Request.Error MISSING_HALL = new Request.Error("Sala non valida");
    public static final Request.Error PERIOD_ERROR = new Request.Error("Periodo non valido");


    private LocalDate start;
    private LocalDate end;
    private String option;
    private String employeeId;
    private LocalTime startShift;
    private LocalTime endShift;
    private int hallId;

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

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalTime getStartShift() {
        return startShift;
    }

    public void setStartShift(LocalTime startShift) {
        this.startShift = startShift;
    }

    public LocalTime getEndShift() {
        return endShift;
    }

    public void setEndShift(LocalTime endShift) {
        this.endShift = endShift;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
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
        }
        if (end == null) {
            addError(MISSING_END);
        }
        if (startShift == null) {
            addError(MISSING_START_SHIFT);
        }
        if (endShift == null) {
            addError(MISSING_END_SHIFT);
        }
        if (start.isAfter(end)) {
            addError(PERIOD_ERROR);
        }
        if (startShift.isAfter(endShift)) {
            addError(PERIOD_ERROR);
        }
    }

    public void semanticValidate(Employee employee, Hall hall) throws InvalidRequestException{
        if(employee == null){
            addError(MISSING_EMPLOYEE);
        }
        if( hall == null && employee instanceof Projectionist){
            addError(MISSING_HALL);
        }
        if(!getErrorList().isEmpty()){
            throw new InvalidRequestException();
        }
    }
}
