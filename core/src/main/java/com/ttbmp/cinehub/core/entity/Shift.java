package com.ttbmp.cinehub.core.entity;

public class Shift {

    private Employee employee;
    private String date;
    private String start;
    private String end;

    public Shift(Employee employee, String date, String start, String end) {
        this.employee = employee;
        this.date = date;
        this.start = start;
        this.end = end;
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
}
