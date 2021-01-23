package com.ttbmp.cinehub.core.usecase.manageemployeesshift.response;

import com.ttbmp.cinehub.core.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.core.dto.ShiftDto;
import com.ttbmp.cinehub.core.entity.Shift;


/**
 * @author Massimo Mazzetti
 */

public class ShiftResponse {
    private ShiftDto shiftDto;

    public ShiftResponse(Shift shift) {
        this.shiftDto = ShiftDataMapper.mapToDto(shift);
    }

    public ShiftDto getShiftDto() {
        return shiftDto;
    }

    public void setShiftDto(ShiftDto shiftDto) {
        this.shiftDto = shiftDto;
    }
}
