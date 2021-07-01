package com.ttbmp.cinehub.app.usecase.viewpersonalschedule.reply;

import com.ttbmp.cinehub.app.dto.EmployeeDto;
import com.ttbmp.cinehub.app.dto.ShiftDto;
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
