package com.ttbmp.cinehub.core;

import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.entity.ShiftProjectionist;

public class ConcreteAbstractShiftProjectionist extends Shift implements AbstractShift {

    public ConcreteAbstractShiftProjectionist(Employee employee, String date, String start, String end, Hall hall) {
        super(employee, date, start, end, hall);
    }

    @Override
    public Shift createShift(Employee employee, String date, String start, String end, Hall hall) {
        return new ShiftProjectionist(employee, date, start, end, hall);
    }

}
