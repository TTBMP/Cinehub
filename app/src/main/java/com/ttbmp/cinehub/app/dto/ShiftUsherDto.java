package com.ttbmp.cinehub.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Fabio Buracchi
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ShiftUsherDto extends ShiftDto {

    public ShiftUsherDto(int id, EmployeeDto employee, LocalDate date, LocalTime start, LocalTime end) {
        super(id, employee, date, start, end);
    }

}
