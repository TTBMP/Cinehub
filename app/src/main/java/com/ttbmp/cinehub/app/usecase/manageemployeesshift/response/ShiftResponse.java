package com.ttbmp.cinehub.app.usecase.manageemployeesshift.response;


import com.ttbmp.cinehub.app.dto.ShiftDto;


/**
 * @author Massimo Mazzetti
 */
public class ShiftResponse {
    private ShiftDto shiftDto;

    public ShiftResponse(ShiftDto shift) {
        this.shiftDto = shift;
    }

    public ShiftDto getShiftDto() {
        return shiftDto;
    }

    public void setShiftDto(ShiftDto shiftDto) {
        this.shiftDto = shiftDto;
    }

}
