package com.ttbmp.cinehub.domain.shift.factory;


import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.employee.Usher;
import com.ttbmp.cinehub.domain.shift.ProjectionistShift;
import com.ttbmp.cinehub.domain.shift.Shift;
import com.ttbmp.cinehub.domain.shift.UsherShift;

import java.time.LocalTime;

public class ShiftFactory {

    public Shift createConcreteShift(Employee employee, String date, String start, String end, Hall hall) throws CreateShiftException {
        if (employee instanceof Projectionist) {
            return createShiftProjectionist(employee, date, start, end, hall);
        } else if (employee instanceof Usher) {
            return createShiftUsher(employee, date, start, end);
        }
        throw new IllegalStateException("Unexpected value: " + employee.getClass());
    }

    private Shift createShiftProjectionist(Employee employee, String date, String start, String end, Hall hall) throws CreateShiftException {
        validateShift(employee, date, start, end);
        return new ProjectionistShift(0, employee, date, start, end, hall, null);
    }

    private Shift createShiftUsher(Employee employee, String date, String start, String end) throws CreateShiftException {
        validateShift(employee, date, start, end);
        return new UsherShift(0, employee, date, start, end);
    }

    private void validateShift(Employee employee, String date, String start, String end) throws CreateShiftException {
        var shiftList = employee.getShiftList();
        for (var shift : shiftList) {
            if (shift.getDate().equals(date)
                    && LocalTime.parse(start).isBefore(LocalTime.parse(shift.getEnd()))
                    && LocalTime.parse(end).isAfter(LocalTime.parse(shift.getStart()))) {
                throw new CreateShiftException(CreateShiftException.ALREADY_EXIST_ERROR);
            }
        }
    }

}
