package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.dto.employee.EmployeeDto;
import com.ttbmp.cinehub.app.dto.employee.EmployeeDtoFactory;
import com.ttbmp.cinehub.app.dto.shift.ShiftDto;
import com.ttbmp.cinehub.app.dto.shift.ShiftDtoFactory;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Buracchi
 */
public class ShiftListReply {

    private EmployeeDto employeeDto;
    private List<ShiftDto> shiftDtoList;

    public ShiftListReply(Employee employee, List<Shift> shiftList) {
        this.employeeDto = EmployeeDtoFactory.getEmployeeDto(employee);
        this.shiftDtoList = shiftList.stream()
                .map(ShiftDtoFactory::getShiftDto)
                .collect(Collectors.toList());
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    public List<ShiftDto> getShiftDtoList() {
        return shiftDtoList;
    }

    public void setShiftDtoList(List<ShiftDto> shiftDtoList) {
        this.shiftDtoList = shiftDtoList;
    }

}
