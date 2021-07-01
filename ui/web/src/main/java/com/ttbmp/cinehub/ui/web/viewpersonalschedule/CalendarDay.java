package com.ttbmp.cinehub.ui.web.viewpersonalschedule;

import com.ttbmp.cinehub.app.dto.ShiftDto;
import lombok.Data;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
@Data
public class CalendarDay {

    private String day;
    private List<ShiftDto> shiftList;

    public CalendarDay(String day, List<ShiftDto> shiftList) {
        this.day = day;
        this.shiftList = shiftList;
    }

}
