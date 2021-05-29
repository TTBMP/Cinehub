package com.ttbmp.cinehub.ui.desktop.manageshift.table;

import com.ttbmp.cinehub.app.dto.employee.EmployeeDto;
import com.ttbmp.cinehub.app.dto.shift.ShiftDto;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Massimo Mazzetti
 */
public class Day {
    private LocalDate date;
    private List<ShiftDto> shiftList;
    private EmployeeDto employee;

    public Day(LocalDate date, List<ShiftDto> shiftList, EmployeeDto employee) {
        this.date = date;
        this.shiftList = shiftList;
        this.employee = employee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ShiftDto> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<ShiftDto> shiftList) {
        this.shiftList = shiftList;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }
}

