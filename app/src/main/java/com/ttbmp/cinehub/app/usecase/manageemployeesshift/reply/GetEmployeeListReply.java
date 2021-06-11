package com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply;

import com.ttbmp.cinehub.app.dto.employee.EmployeeDto;

import java.util.List;

public class GetEmployeeListReply {

    private List<EmployeeDto> employeeDtoList;

    public GetEmployeeListReply(List<EmployeeDto> employeeDtoList) {
        this.employeeDtoList = employeeDtoList;
    }

    public List<EmployeeDto> getEmployeeDtoList() {
        return employeeDtoList;
    }

    public void setEmployeeDtoList(List<EmployeeDto> employeeDtoList) {
        this.employeeDtoList = employeeDtoList;
    }
}
