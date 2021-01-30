package com.ttbmp.cinehub.core.usecase.manageemployeesshift.request;

import com.ttbmp.cinehub.core.dto.ShiftDto;
import com.ttbmp.cinehub.core.usecase.Request;

import java.time.LocalDate;


public class ShiftRepeatRequest extends Request {
    public static final Request.Error MISSING_SHIFT = new Request.Error("turni non validi");
    public static final Request.Error MISSING_START = new Request.Error("inizio non valido");
    public static final Request.Error MISSING_END = new Request.Error("fine non valida");
    public static final Request.Error MISSING_OPTION = new Request.Error("opzione non valida");

    private LocalDate start;
    private LocalDate end;
    private String option;
    private ShiftDto shift;

    public ShiftRepeatRequest(LocalDate start, LocalDate end, String option, ShiftDto shift) {
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

    public ShiftDto getShift() {
        return shift;
    }

    public void setShift(ShiftDto shift) {
        this.shift = shift;
    }

    @Override
    public void onValidate() {
        if (shift == null) {
            addError(MISSING_SHIFT);
        }
        if (option == null) {
            addError(MISSING_OPTION);
        }
        if (start == null) {
            addError(MISSING_START);
        }
        if (end == null) {
            addError(MISSING_END);
        }
    }
}
