package com.ttbmp.cinehub.app.dto.shift;

import com.ttbmp.cinehub.domain.shift.UsherShift;
import lombok.Value;

/**
 * @author Fabio Buracchi
 */
@Value
public class ShiftUsherDto extends ShiftDto {

    public ShiftUsherDto(UsherShift shift) {
        super(shift);
    }

}
