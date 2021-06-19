package com.ttbmp.cinehub.ui.web.manageemployeeshift.form;

import com.ttbmp.cinehub.app.dto.employee.EmployeeDto;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeListDto {

    private List<EmployeeDto> employeeList;

    public EmployeeListDto(List<EmployeeDto> employeeDtoList) {
        employeeList = employeeDtoList;
    }

}
