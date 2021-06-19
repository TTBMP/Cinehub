package com.ttbmp.cinehub.app.dto.shift;

import com.ttbmp.cinehub.domain.shift.Shift;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public abstract class ShiftDto {

    int id;
    String employeeId;
    LocalDate date;
    LocalTime start;
    LocalTime end;

    protected ShiftDto(Shift shift) {
        this.id = shift.getId();
        this.employeeId = shift.getEmployee().getId();
        this.date = LocalDate.parse(shift.getDate());
        this.start = LocalTime.parse(shift.getStart());
        this.end = LocalTime.parse(shift.getEnd());
    }

}
