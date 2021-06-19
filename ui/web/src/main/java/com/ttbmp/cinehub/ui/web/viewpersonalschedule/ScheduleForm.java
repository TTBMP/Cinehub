package com.ttbmp.cinehub.ui.web.viewpersonalschedule;

import com.ttbmp.cinehub.ui.web.domain.Employee;
import com.ttbmp.cinehub.ui.web.domain.Shift;
import lombok.Data;

@Data
public class ScheduleForm {

    private Employee employee;
    private Shift shift;

}
