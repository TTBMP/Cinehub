package com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply;

import com.ttbmp.cinehub.app.dto.EmployeeDto;
import lombok.Value;

import java.util.List;

@Value
public class GetEmployeeListReply {

    List<EmployeeDto> employeeDtoList;

}
