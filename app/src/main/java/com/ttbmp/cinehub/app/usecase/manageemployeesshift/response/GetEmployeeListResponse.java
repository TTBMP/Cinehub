package com.ttbmp.cinehub.app.usecase.manageemployeesshift.response;

import com.ttbmp.cinehub.app.dto.EmployeeDto;

import java.time.LocalDate;
import java.util.List;

public class GetEmployeeListResponse {

    List<EmployeeDto> employeeDtoList;
    LocalDate date;

    public GetEmployeeListResponse() {
    }

    public GetEmployeeListResponse(List<EmployeeDto> employeeDtoList, LocalDate date) {
        this.employeeDtoList = employeeDtoList;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<EmployeeDto> getEmployeeDtoList() {
        return employeeDtoList;
    }

    public void setEmployeeDtoList(List<EmployeeDto> employeeDtoList) {
        this.employeeDtoList = employeeDtoList;
    }
}
