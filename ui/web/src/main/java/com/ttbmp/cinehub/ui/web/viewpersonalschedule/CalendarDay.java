package com.ttbmp.cinehub.ui.web.viewpersonalschedule;

import com.ttbmp.cinehub.app.dto.ShiftDto;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class CalendarDay {

    private String day;
    private List<ShiftDto> shiftList;

    public CalendarDay(String day, List<ShiftDto> shiftList) {
        this.day = day;
        this.shiftList = shiftList;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<ShiftDto> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<ShiftDto> shiftList) {
        this.shiftList = shiftList;
    }

}
