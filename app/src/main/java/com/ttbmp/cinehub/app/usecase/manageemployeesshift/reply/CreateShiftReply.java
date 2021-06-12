package com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply;

import com.ttbmp.cinehub.app.dto.shift.ShiftDto;

public class CreateShiftReply {

    private ShiftDto shiftDto;

    public CreateShiftReply(ShiftDto shiftDto) {
        this.shiftDto = shiftDto;
    }

    public ShiftDto getShiftDto() {
        return shiftDto;
    }

    public void setShiftDto(ShiftDto shiftDto) {
        this.shiftDto = shiftDto;
    }

}
