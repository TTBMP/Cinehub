package com.ttbmp.cinehub.app.dto.shift;

import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.domain.shift.UsherShift;

public class ShiftDtoFactory {

    private ShiftDtoFactory() {

    }

    public static ShiftDto getShiftDto(Shift shift) {
        if (shift instanceof ProjectionistShift) {
            return getShiftDto((ProjectionistShift) shift);
        }
        if (shift instanceof UsherShift) {
            return getShiftDto((UsherShift) shift);
        }
        throw new IllegalStateException("Unexpected value: " + shift);
    }

    public static ShiftDto getShiftDto(ProjectionistShift shift) {
        return new ShiftProjectionistDto(shift);
    }

    public static ShiftDto getShiftDto(UsherShift shift) {
        return new ShiftUsherDto(shift);
    }

}
