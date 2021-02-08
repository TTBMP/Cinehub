package com.ttbmp.cinehub.domain.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.domain.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.domain.dto.ShiftDto;
import com.ttbmp.cinehub.domain.entity.shift.Shift;

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
