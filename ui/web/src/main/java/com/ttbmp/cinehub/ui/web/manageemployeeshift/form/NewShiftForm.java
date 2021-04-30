package com.ttbmp.cinehub.ui.web.manageemployeeshift.form;

import java.time.LocalDate;
import java.time.LocalTime;

public class NewShiftForm {

    int hallId;
    private String employeeId;
    private LocalDate date;
    private LocalTime inizio;
    private LocalTime end;

    public NewShiftForm() {

    }

    public NewShiftForm(String employeeId, LocalDate date, LocalTime inizio, LocalTime end, int hallId) {
        this.employeeId = employeeId;
        this.date = date;
        this.inizio = inizio;
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

    public LocalTime getInizio() {
        return inizio;
    }

    public void setInizio(LocalTime inizio) {
        this.inizio = inizio;
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
