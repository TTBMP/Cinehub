package com.ttbmp.cinehub.app.usecase.manageemployeesshift.reply;

import com.ttbmp.cinehub.app.dto.shift.ShiftDto;

/**
 * @author Massimo Mazzetti
 */
public class ShiftReply {
    private ShiftDto shiftDto;

    public ShiftReply(ShiftDto shift) {
        this.shiftDto = shift;
    }

    public ShiftDto getShiftDto() {
        return shiftDto;
    }

    public void setShiftDto(ShiftDto shiftDto) {
        this.shiftDto = shiftDto;
    }

}
