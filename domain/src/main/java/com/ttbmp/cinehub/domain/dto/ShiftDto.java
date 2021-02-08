package com.ttbmp.cinehub.domain.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public class ShiftDto {

    private final LocalTime start;
    private EmployeeDto employee;
    private String employeeName;
    private LocalDate date;
    private LocalTime end;


    public ShiftDto(String employeeName, LocalDate date, LocalTime start, LocalTime end) {
        this.employeeName = employeeName;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public ShiftDto(EmployeeDto employee, LocalDate date, LocalTime start, LocalTime end) {
        this.employee = employee;
        this.date = date;
        this.start = start;
        this.end = end;
    }


    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public String getEmployeeName() {
        return employeeName;
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

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }


}
