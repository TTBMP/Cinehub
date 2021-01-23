package com.ttbmp.cinehub.app.client.desktop.ui.manageshift.table;

import com.ttbmp.cinehub.core.dto.EmployeeDto;

public class EmployeeShiftWeek {
    private DayWeek lun, mar, mer, gio, ven, sab, dom;
    private EmployeeDto employeeDto;

    public EmployeeShiftWeek(EmployeeDto employeeDto, DayWeek lun, DayWeek mar, DayWeek mer, DayWeek gio, DayWeek ven, DayWeek sab, DayWeek dom) {
        this.employeeDto = employeeDto;
        this.lun = lun;
        this.mar = mar;
        this.mer = mer;
        this.gio = gio;
        this.ven = ven;
        this.sab = sab;
        this.dom = dom;
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    public DayWeek getLun() {
        return lun;
    }

    public void setLun(DayWeek lun) {
        this.lun = lun;
    }

    public DayWeek getMar() {
        return mar;
    }

    public void setMar(DayWeek mar) {
        this.mar = mar;
    }

    public DayWeek getMer() {
        return mer;
    }

    public void setMer(DayWeek mer) {
        this.mer = mer;
    }

    public DayWeek getGio() {
        return gio;
    }

    public void setGio(DayWeek gio) {
        this.gio = gio;
    }

    public DayWeek getVen() {
        return ven;
    }

    public void setVen(DayWeek ven) {
        this.ven = ven;
    }

    public DayWeek getSab() {
        return sab;
    }

    public void setSab(DayWeek sab) {
        this.sab = sab;
    }

    public DayWeek getDom() {
        return dom;
    }

    public void setDom(DayWeek dom) {
        this.dom = dom;
    }
}
