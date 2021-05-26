package com.ttbmp.cinehub.app.dto.shift;

import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;

/**
 * @author Fabio Buracchi
 */
public class ShiftProjectionistDto extends ShiftDto {

    private final HallDto hallDto;

    public ShiftProjectionistDto(ProjectionistShift shift) {
        super(shift);
        this.hallDto = new HallDto(shift.getHall());
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
