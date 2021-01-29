package com.ttbmp.cinehub.core.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShiftProjectionistDto extends ShiftDto {


    public ShiftProjectionistDto(EmployeeDto employee, LocalDate date, LocalTime start, LocalTime end, HallDto hallDto) {
        super(employee, date, start, end, hallDto);

    }


}
