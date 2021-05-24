package com.ttbmp.cinehub.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Fabio Buracchi
 */
public class ShiftUsherDto extends ShiftDto {

    public ShiftUsherDto(int id, String employeeId, LocalDate date, LocalTime start, LocalTime end) {
        super(id, employeeId, date, start, end);
    }

}
