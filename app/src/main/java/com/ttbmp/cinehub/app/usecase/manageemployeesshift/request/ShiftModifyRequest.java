package com.ttbmp.cinehub.app.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.app.dto.ProjectionistDto;
import com.ttbmp.cinehub.app.utilities.request.AuthenticatedRequest;
import com.ttbmp.cinehub.app.utilities.request.Request;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShiftModifyRequest extends AuthenticatedRequest {
    public static final Request.Error MISSING_SHIFT = new Request.Error("Shift non valido");
    public static final Request.Error MISSING_DATE = new Request.Error("Data non valido");
    public static final Request.Error MISSING_START = new Request.Error("Inizio non valido");
    public static final Request.Error MISSING_END = new Request.Error("Fine non valida");
    public static final Request.Error MISSING_HALL = new Request.Error("Hall non valida");
    public static final Request.Error MISSING_EMPLOYEE = new Request.Error("Dipendente non valido");
    public static final Request.Error ERROR_TIME = new Request.Error("Orario non Valido");

    private EmployeeDto employeeDto;
    private int shiftId;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private HallDto hall;

    public ShiftModifyRequest(String sessionToken,EmployeeDto employeeDto, int shiftId, LocalDate date, LocalTime start, LocalTime end, HallDto hall) {
        super(sessionToken);
        this.employeeDto = employeeDto;
        this.shiftId = shiftId;
        this.date = date;
        this.start = start;
        this.end = end;
        this.hall = hall;
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
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

    public HallDto getHall() {
        return hall;
    }

    public void setHall(HallDto hall) {
        this.hall = hall;
    }

    @Override
    protected void onValidate() {
        if (shiftId == -1) {
            addError(MISSING_SHIFT);
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
        if (hall == null && employeeDto instanceof ProjectionistDto) {
            addError(MISSING_HALL);
        }
        if (employeeDto == null) {
            addError(MISSING_EMPLOYEE);
        }
        if (start.isAfter(end)) {
            addError(ERROR_TIME);
        }

    }
}
