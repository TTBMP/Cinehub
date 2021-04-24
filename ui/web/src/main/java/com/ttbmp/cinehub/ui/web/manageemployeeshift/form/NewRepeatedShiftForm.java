package com.ttbmp.cinehub.ui.web.manageemployeeshift.form;

import java.time.LocalDate;
import java.time.LocalTime;

public class NewRepeatedShiftForm {

    private String employeeId;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private int hallId;
    private String preference;
    private LocalDate dateRepeated;

    public NewRepeatedShiftForm() {
    }

    public NewRepeatedShiftForm(String employeeId, LocalDate date, LocalTime start, LocalTime end, int hallId, String preference, LocalDate dateRepeated) {
        this.employeeId = employeeId;
        this.date = date;
        this.start = start;
        this.end = end;
        this.hallId = hallId;
        this.preference = preference;
        this.dateRepeated = dateRepeated;
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

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public LocalDate getDateRepeated() {
        return dateRepeated;
    }

    public void setDateRepeated(LocalDate dateRepeated) {
        this.dateRepeated = dateRepeated;
    }
}
