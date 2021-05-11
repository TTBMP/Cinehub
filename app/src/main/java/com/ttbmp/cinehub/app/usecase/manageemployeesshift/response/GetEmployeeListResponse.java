package com.ttbmp.cinehub.app.usecase.manageemployeesshift.response;

import com.ttbmp.cinehub.app.dto.EmployeeDto;

import java.time.LocalDate;
import java.util.List;

public class GetEmployeeListResponse {

    private List<EmployeeDto> employeeDtoList;

    public GetEmployeeListResponse() {
    }

    public GetEmployeeListResponse(List<EmployeeDto> employeeDtoList) {
        this.employeeDtoList = employeeDtoList;
    }


    public List<EmployeeDto> getEmployeeDtoList() {
        return employeeDtoList;
    }

    public void setEmployeeDtoList(List<EmployeeDto> employeeDtoList) {
        this.employeeDtoList = employeeDtoList;
    }
}
