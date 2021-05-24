package com.ttbmp.cinehub.ui.web.manageemployeeshift;

import com.ttbmp.cinehub.app.dto.EmployeeDto;

import java.util.List;

public class EmployeeListDto {

    private List<EmployeeDto> employeeList;

    public EmployeeListDto(List<EmployeeDto> employeeList) {
        this.employeeList = employeeList;
    }

    public List<EmployeeDto> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeDto> employeeList) {
        this.employeeList = employeeList;
    }
}
