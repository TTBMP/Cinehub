package com.ttbmp.cinehub.core.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.usecase.Request;

import java.time.LocalDate;


public class ShiftRepeatRequest extends Request {

    private LocalDate start;
    private LocalDate end;
    private String option;
    private Shift shift;

    public ShiftRepeatRequest(LocalDate start, LocalDate end, String option, Shift shift) {
        this.start = start;
        this.end = end;
        this.option = option;
        this.shift = shift;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    @Override
    public void onValidate() {

    }
}
