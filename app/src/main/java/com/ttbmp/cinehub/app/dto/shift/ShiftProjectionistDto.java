package com.ttbmp.cinehub.app.dto.shift;

import com.ttbmp.cinehub.app.dto.HallDto;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * @author Fabio Buracchi
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class ShiftProjectionistDto extends ShiftDto {

    HallDto hallDto;

    public ShiftProjectionistDto(ProjectionistShift shift) {
        super(shift);
        this.hallDto = new HallDto(shift.getHall());
    }

}
