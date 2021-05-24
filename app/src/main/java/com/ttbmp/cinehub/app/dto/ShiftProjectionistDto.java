package com.ttbmp.cinehub.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Fabio Buracchi
 */
public class ShiftProjectionistDto extends ShiftDto {

    private final HallDto hallDto;

    public ShiftProjectionistDto(int id, String employeeId, LocalDate date, LocalTime start, LocalTime end, HallDto hallDto) {
        super(id, employeeId, date, start, end);
        this.hallDto = hallDto;
    }

    public HallDto getHallDto() {
        return hallDto;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
