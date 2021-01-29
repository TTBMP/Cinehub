package com.ttbmp.cinehub.core.usecase.manageemployeesshift.response;


import com.ttbmp.cinehub.core.dto.ShiftDto;

import java.util.List;


public class ShiftRepeatResponse {
    private List<ShiftDto> shiftDto;

    public ShiftRepeatResponse(List<ShiftDto> shiftDto) {
        this.shiftDto = shiftDto;
    }

    public List<ShiftDto> getShiftDto() {
        return shiftDto;
    }

    public void setShiftDto(List<ShiftDto> shiftDto) {
        this.shiftDto = shiftDto;
    }
}
