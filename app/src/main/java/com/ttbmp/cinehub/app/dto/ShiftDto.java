package com.ttbmp.cinehub.app.dto;

import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.domain.shift.UsherShift;
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
public class ShiftDto {

    int id;
    String employeeId;
    LocalDate date;
    LocalTime start;
    LocalTime end;
    ShiftType type;
    HallDto hall;

    public ShiftDto(Shift shift) {
        this.id = shift.getId();
        this.employeeId = shift.getEmployee().getId();
        this.date = LocalDate.parse(shift.getDate());
        this.start = LocalTime.parse(shift.getStart());
        this.end = LocalTime.parse(shift.getEnd());
        if (shift instanceof ProjectionistShift) {
            this.type = ShiftType.PROJECTIONIST_SHIFT;
            this.hall = new HallDto(((ProjectionistShift) shift).getHall());
        } else if (shift instanceof UsherShift) {
            this.type = ShiftType.USHER_SHIFT;
            this.hall = null;
        } else {
            throw new IllegalStateException("Unexpected value: " + shift);
        }
    }

    public enum ShiftType {
        PROJECTIONIST_SHIFT("ProjectionistShift"),
        USHER_SHIFT("UsherShift");

        private final String type;

        ShiftType(final String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }

    }

}
