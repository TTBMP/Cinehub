package com.ttbmp.cinehub.ui.desktop.ui.manageshift.table;

import com.ttbmp.cinehub.app.dto.EmployeeDto;

import java.time.DayOfWeek;
import java.util.Map;

/**
 * @author Massimo Mazzetti
 */
public class EmployeeShiftWeek {

    private EmployeeDto employeeDto;
    private Map<DayOfWeek, DayWeek> weekMap;

    public EmployeeShiftWeek(EmployeeDto employeeDto, Map<DayOfWeek, DayWeek> weekMap) {
        this.employeeDto = employeeDto;
        this.weekMap = weekMap;
    }

    public DayWeek getDayOfWeek(DayOfWeek dayOfWeek) {
        return weekMap.get(dayOfWeek);
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    public Map<DayOfWeek, DayWeek> getWeekMap() {
        return weekMap;
    }

    public void setWeekMap(Map<DayOfWeek, DayWeek> weekMap) {
        this.weekMap = weekMap;
    }
}
