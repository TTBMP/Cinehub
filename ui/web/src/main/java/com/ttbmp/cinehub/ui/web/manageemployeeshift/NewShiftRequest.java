package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import java.time.LocalDate;
import java.time.LocalTime;

public class NewShiftRequest {

    String employeeId;
    LocalDate date;
    LocalTime start;
    LocalTime end;
    int hallId;

    public NewShiftRequest() {

    }

    public NewShiftRequest(String employeeId, LocalDate date, LocalTime start, LocalTime end, int hallId) {
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
}
