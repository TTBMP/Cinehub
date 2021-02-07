package com.ttbmp.cinehub.core.usecase.manageemployeesshift.response;

import com.ttbmp.cinehub.core.dto.ShiftDto;

public class CreateShiftResponse {

    private ShiftDto shiftDto;

    public CreateShiftResponse(ShiftDto shiftDto) {
        this.shiftDto = shiftDto;
    }

    public ShiftDto getShiftDto() {
        return shiftDto;
    }

    public void setShiftDto(ShiftDto shiftDto) {
        this.shiftDto = shiftDto;
    }
}
