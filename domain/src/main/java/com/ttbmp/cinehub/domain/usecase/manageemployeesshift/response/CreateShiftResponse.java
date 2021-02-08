package com.ttbmp.cinehub.domain.usecase.manageemployeesshift.response;

import com.ttbmp.cinehub.domain.dto.ShiftDto;

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
