package com.ttbmp.cinehub.domain.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShiftProjectionistDto extends ShiftDto {
    private final HallDto hallDto;

    public ShiftProjectionistDto(EmployeeDto employee, LocalDate date, LocalTime start, LocalTime end, HallDto hallDto) {
        super(employee, date, start, end);
        this.hallDto = hallDto;
    }

    public HallDto getHallDto() {
        return hallDto;
    }
}
