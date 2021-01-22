package com.ttbmp.cinehub.core.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Fabio Buracchi
 */
public class ShiftDto {

    private String employeeName;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;

    public ShiftDto(String employeeName, LocalDate date, LocalTime start, LocalTime end) {
        this.employeeName = employeeName;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

}
