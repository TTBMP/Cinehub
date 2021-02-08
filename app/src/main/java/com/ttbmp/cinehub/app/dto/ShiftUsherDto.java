package com.ttbmp.cinehub.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShiftUsherDto extends ShiftDto {

    public ShiftUsherDto(EmployeeDto employee, LocalDate date, LocalTime start, LocalTime end) {
        super(employee, date, start, end);
    }
}
