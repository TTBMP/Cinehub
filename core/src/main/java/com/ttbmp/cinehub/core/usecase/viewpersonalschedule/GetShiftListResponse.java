package com.ttbmp.cinehub.core.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.core.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.core.dto.ShiftDto;
import com.ttbmp.cinehub.core.entity.shift.Shift;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class GetShiftListResponse {

    private List<ShiftDto> shiftDtoList;

    public GetShiftListResponse(List<Shift> shiftList) {
        this.shiftDtoList = ShiftDataMapper.mapToDtoList(shiftList);
    }

    public List<ShiftDto> getShiftDtoList() {
        return shiftDtoList;
    }

    public void setShiftDtoList(List<ShiftDto> shiftDtoList) {
        this.shiftDtoList = shiftDtoList;
    }

}
