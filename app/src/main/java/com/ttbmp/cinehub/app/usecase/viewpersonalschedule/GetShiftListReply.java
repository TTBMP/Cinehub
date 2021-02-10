package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.datamapper.ShiftDataMapper;
import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.domain.shift.Shift;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class GetShiftListReply {

    private List<ShiftDto> shiftDtoList;

    public GetShiftListReply(List<Shift> shiftList) {
        this.shiftDtoList = ShiftDataMapper.mapToDtoList(shiftList);
    }

    public List<ShiftDto> getShiftDtoList() {
        return shiftDtoList;
    }

    public void setShiftDtoList(List<ShiftDto> shiftDtoList) {
        this.shiftDtoList = shiftDtoList;
    }

}
