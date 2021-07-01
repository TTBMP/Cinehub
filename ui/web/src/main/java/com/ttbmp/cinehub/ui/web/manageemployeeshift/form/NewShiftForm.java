package com.ttbmp.cinehub.ui.web.manageemployeeshift.form;

import com.ttbmp.cinehub.app.dto.ShiftDto;
import com.ttbmp.cinehub.ui.web.domain.Employee;
import com.ttbmp.cinehub.ui.web.domain.Hall;
import lombok.Data;

import java.time.LocalTime;

@Data
public class NewShiftForm {

    private int shiftId;
    private Hall hall;
    private Employee employee;
    private String date;
    private LocalTime start;
    private LocalTime end;
    private String type;
    private boolean change;

}
