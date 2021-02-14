package com.ttbmp.cinehub.domain.shift;

import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.employee.Employee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public abstract class Shift {

    private int id;
    private Employee employee;
    private String date;
    private String start;
    private String end;

    protected Shift(int id, Employee employee, String date, String start, String end) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public abstract void modifyShift(Shift shift, LocalDate date, LocalTime start, LocalTime end, Hall hall) throws ModifyShiftException;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Shift other = (Shift) obj;
        return id == other.getId();
    }

    @Override
    public int hashCode() {
        return id;
    }

}
