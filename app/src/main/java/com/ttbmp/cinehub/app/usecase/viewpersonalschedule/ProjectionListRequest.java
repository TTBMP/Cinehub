package com.ttbmp.cinehub.app.usecase.viewpersonalschedule;

import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.app.usecase.Request;

/**
 * @author Fabio Buracchi
 */
public class ProjectionListRequest extends Request {

    public static final Request.Error MISSING_SHIFT_ERROR = new Request.Error("Shift value can't be null");
    public static final Request.Error INVALID_SHIFT_ERROR = new Request.Error("Shift value is not valid");

    private ShiftDto shiftDto;

    public ProjectionListRequest(ShiftDto shiftDto) {
        this.shiftDto = shiftDto;
    }

    public ShiftDto getShiftDto() {
        return shiftDto;
    }

    public void setShiftDto(ShiftDto shiftDto) {
        this.shiftDto = shiftDto;
    }

    @Override
    protected void onValidate() {
        if (shiftDto == null) {
            addError(MISSING_SHIFT_ERROR);
        } else {
            if (shiftDto.getEmployee() == null
                    || shiftDto.getDate() == null
                    || shiftDto.getStart() == null
                    || shiftDto.getEnd() == null) {
                addError(INVALID_SHIFT_ERROR);
            }
        }
    }

}
