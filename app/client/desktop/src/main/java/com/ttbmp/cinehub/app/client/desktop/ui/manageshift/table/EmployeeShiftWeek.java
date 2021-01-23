package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table;

import com.ttbmp.cinehub.core.dto.EmployeeDto;

/**
 * @author Massimo Mazzetti
 */


public class EmployeeShiftWeek {
    private DayWeek monday;
    private DayWeek tuesday;
    private DayWeek wednesday;
    private DayWeek thursday;
    private DayWeek friday;
    private DayWeek saturday;
    private DayWeek sunday;
    private EmployeeDto employeeDto;

    public EmployeeShiftWeek(EmployeeDto employeeDto, DayWeek monday, DayWeek tuesday, DayWeek wednesday, DayWeek thursday, DayWeek friday, DayWeek saturday, DayWeek sunday) {
        this.employeeDto = employeeDto;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    public DayWeek getMonday() {
        return monday;
    }

    public void setMonday(DayWeek monday) {
        this.monday = monday;
    }

    public DayWeek getTuesday() {
        return tuesday;
    }

    public void setTuesday(DayWeek tuesday) {
        this.tuesday = tuesday;
    }

    public DayWeek getWednesday() {
        return wednesday;
    }

    public void setWednesday(DayWeek wednesday) {
        this.wednesday = wednesday;
    }

    public DayWeek getThursday() {
        return thursday;
    }

    public void setThursday(DayWeek thursday) {
        this.thursday = thursday;
    }

    public DayWeek getFriday() {
        return friday;
    }

    public void setFriday(DayWeek friday) {
        this.friday = friday;
    }

    public DayWeek getSaturday() {
        return saturday;
    }

    public void setSaturday(DayWeek saturday) {
        this.saturday = saturday;
    }

    public DayWeek getSunday() {
        return sunday;
    }

    public void setSunday(DayWeek sunday) {
        this.sunday = sunday;
    }
}
