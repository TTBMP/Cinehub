package com.ttbmp.cinehub.domain.shift;

import com.ttbmp.cinehub.domain.Employee;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
public abstract class Shift {

    private Employee employee;
    private String date;
    private String start;
    private String end;

    protected Shift(Employee employee, String date, String start, String end) {
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
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Shift other = (Shift) obj;
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
