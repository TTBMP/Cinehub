package com.ttbmp.cinehub.app.usecase.manageemployeesshift.response;

import com.ttbmp.cinehub.app.dto.shift.ShiftDto;

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
