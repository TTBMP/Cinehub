package com.ttbmp.cinehub.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public class ShiftDto {

    private EmployeeDto employee;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;

    public ShiftDto() {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        ShiftDto other = (ShiftDto) obj;
        return employee.equals(other.employee)
                && date.equals(other.date)
                && start.equals(other.start)
                && end.equals(other.end);
    }

    @Override
    public int hashCode() {
        return employee.hashCode() + date.hashCode() + start.hashCode() + end.hashCode();
    }

}
