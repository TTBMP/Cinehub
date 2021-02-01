package com.ttbmp.cinehub.core;

import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.Hall;


public class ShiftFactory {

    public ConcreteAbstractShiftProjectionist createShiftProjectionist(Employee employee, String date, String start, String end, Hall hall) {
        return new ConcreteAbstractShiftProjectionist(employee, date, start, end, hall);
    }

    public ConcreteAbstractShiftUsher createShiftUsher(Employee employee, String date, String start, String end) {
        return new ConcreteAbstractShiftUsher(employee, date, start, end);
    }
}
