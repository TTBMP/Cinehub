package com.ttbmp.cinehub.core.usecase.manageemployeesshift.response;

import com.ttbmp.cinehub.core.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.core.dto.ShiftDto;
import com.ttbmp.cinehub.core.entity.Shift;

public class GetShiftResponse {
    private ShiftDto shiftDto;

    public GetShiftResponse(Shift shift) {
        this.shiftDto = ShiftDataMapper.mapToDto(shift);
    }

    public ShiftDto getShiftDto() {
        return shiftDto;
    }

    public void setShiftDto(ShiftDto shiftDto) {
        this.shiftDto = shiftDto;
    }
}
