package com.ttbmp.cinehub.ui.web.manageemployeeshift.form;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class NewRepeatedShiftForm {

    private String employeeId;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private int hallId;
    private String preference;
    private LocalDate dateRepeated;

}
