package com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply;

import com.ttbmp.cinehub.app.dto.shift.ShiftDto;

import java.util.List;

public class ShiftRepeatReply {
    private List<ShiftDto> shiftDto;

    public ShiftRepeatReply(List<ShiftDto> shiftDto) {
        this.shiftDto = shiftDto;
    }

    public List<ShiftDto> getShiftDto() {
        return shiftDto;
    }

    public void setShiftDto(List<ShiftDto> shiftDto) {
        this.shiftDto = shiftDto;
    }
}
