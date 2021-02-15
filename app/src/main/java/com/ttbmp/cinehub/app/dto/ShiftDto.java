package com.ttbmp.cinehub.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ShiftDto {

    private int id;
    private EmployeeDto employee;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;

    public ShiftDto(int id, EmployeeDto employee, LocalDate date, LocalTime start, LocalTime end) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
