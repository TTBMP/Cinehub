package com.ttbmp.cinehub.core.entity;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public abstract class Shift {

    private Employee employee;
    private String date;
    private String start;
    private String end;

    public Shift() {
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Shift elem = (Shift) obj;

        return this.getEmployee().equals(elem.getEmployee())
                && this.getDate().equals(elem.getDate())
                && this.getStart().equals(elem.getStart())
                && this.getEnd().equals(elem.getEnd());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}