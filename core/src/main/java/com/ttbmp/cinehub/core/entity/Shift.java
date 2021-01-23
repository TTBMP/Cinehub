package com.ttbmp.cinehub.core.entity;

/**
 * @author Fabio Buracchi
 */
public class Shift {

    private Employee employee;
    private String date;
    private final String start;
    private String end;
    private Hall hall;

    public Shift(Employee employee, String date, String start, String end) {
        this.employee = employee;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public Shift(Employee employee, String date, String start, String end , Hall hall) {
        this.employee = employee;
        this.date = date;
        this.start = start;
        this.end = end;
        this.hall=hall;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Employee getEmployee() {
        return employee;
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

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object obj){
        Shift elem = (Shift) obj;
        return this.getEmployee().equals(elem.getEmployee())
                && this.getDate().equals(elem.getDate())
                && this.getStart().equals(elem.getStart())
                && this.getEnd().equals(elem.getEnd());
    }

}
