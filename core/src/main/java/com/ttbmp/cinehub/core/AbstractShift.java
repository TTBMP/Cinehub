package com.ttbmp.cinehub.core;

import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.entity.Shift;

public interface AbstractShift {

    Shift createShift(Employee employee, String date, String start, String end, Hall hall);

}
