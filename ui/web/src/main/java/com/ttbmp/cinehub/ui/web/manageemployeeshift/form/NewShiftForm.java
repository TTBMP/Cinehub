package com.ttbmp.cinehub.ui.web.manageemployeeshift.form;

import com.ttbmp.cinehub.ui.web.domain.Employee;
import com.ttbmp.cinehub.ui.web.domain.Hall;

import java.time.LocalTime;

public class NewShiftForm {

    private int shiftId;
    private Hall hall;
    private Employee employee;
    private String date;
    private LocalTime start;
    private LocalTime end;
    private boolean change;

    public NewShiftForm() {

    }

    public NewShiftForm(int shiftId, Hall hall, Employee employee, String date, LocalTime start, LocalTime end, boolean change) {
        this.shiftId = shiftId;
        this.hall = hall;
        this.employee = employee;
        this.date = date;
        this.start = start;
        this.end = end;
        this.change = change;
    }

    public boolean isChange() {
        return change;
    }

    public void setChange(boolean change) {
        this.change = change;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
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

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
