package com.ttbmp.cinehub.core;

import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.entity.Shift;
import com.ttbmp.cinehub.core.entity.ShiftUsher;


public class ConcreteAbstractShiftUsher extends Shift implements AbstractShift {
    public ConcreteAbstractShiftUsher(Employee employee, String date, String start, String end) {
        super(employee, date, start, end);
    }

    @Override
    public Shift createShift(Employee employee, String date, String start, String end, Hall hall) {
        return new ShiftUsher(employee, date, start, end);
    }
}