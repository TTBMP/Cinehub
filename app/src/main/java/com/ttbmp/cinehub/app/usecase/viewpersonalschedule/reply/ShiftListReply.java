package com.ttbmp.cinehub.app.usecase.viewpersonalschedule.reply;

import com.ttbmp.cinehub.app.dto.employee.EmployeeDto;
import com.ttbmp.cinehub.app.dto.shift.ShiftDto;
import lombok.Value;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
@Value
public class ShiftListReply {

    EmployeeDto employeeDto;
    List<ShiftDto> shiftDtoList;

}
