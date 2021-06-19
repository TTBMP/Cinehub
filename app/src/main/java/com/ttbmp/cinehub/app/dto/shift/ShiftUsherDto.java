package com.ttbmp.cinehub.app.dto.shift;

import com.ttbmp.cinehub.domain.shift.UsherShift;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * @author Fabio Buracchi
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class ShiftUsherDto extends ShiftDto {

    public ShiftUsherDto(UsherShift shift) {
        super(shift);
    }

}
