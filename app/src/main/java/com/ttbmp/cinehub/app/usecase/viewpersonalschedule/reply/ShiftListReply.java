package com.ttbmp.cinehub.app.usecase.viewpersonalschedule.reply;

import com.ttbmp.cinehub.app.dto.employee.EmployeeDto;
import com.ttbmp.cinehub.app.dto.shift.ShiftDto;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class ShiftListReply {

    private EmployeeDto employeeDto;
    private List<ShiftDto> shiftDtoList;

    public ShiftListReply(EmployeeDto employeeDto, List<ShiftDto> shiftDtoList) {
        this.employeeDto = employeeDto;
        this.shiftDtoList = shiftDtoList;
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
