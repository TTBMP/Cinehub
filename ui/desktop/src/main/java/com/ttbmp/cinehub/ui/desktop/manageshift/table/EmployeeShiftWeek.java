package com.ttbmp.cinehub.ui.desktop.manageshift.table;

import com.ttbmp.cinehub.app.dto.employee.EmployeeDto;

import java.time.DayOfWeek;
import java.util.Map;

/**
 * @author Massimo Mazzetti
 */
public class EmployeeShiftWeek {

    private EmployeeDto employeeDto;
    private Map<DayOfWeek, Day> weekMap;

    public EmployeeShiftWeek(EmployeeDto employeeDto, Map<DayOfWeek, Day> weekMap) {
        this.employeeDto = employeeDto;
        this.weekMap = weekMap;
    }

    public Day getDayOfWeek(DayOfWeek dayOfWeek) {
        return weekMap.get(dayOfWeek);
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    public Map<DayOfWeek, Day> getWeekMap() {
        return weekMap;
    }

    public void setWeekMap(Map<DayOfWeek, Day> weekMap) {
        this.weekMap = weekMap;
    }
}
