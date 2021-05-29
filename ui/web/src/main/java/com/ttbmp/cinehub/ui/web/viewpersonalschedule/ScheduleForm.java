package com.ttbmp.cinehub.ui.web.viewpersonalschedule;

import com.ttbmp.cinehub.ui.web.domain.Employee;
import com.ttbmp.cinehub.ui.web.domain.Shift;

public class ScheduleForm {

    private Employee employee;
    private Shift shift;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

}
