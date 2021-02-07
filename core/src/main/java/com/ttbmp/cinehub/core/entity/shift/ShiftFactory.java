package com.ttbmp.cinehub.core.entity.shift;


import com.ttbmp.cinehub.core.entity.Employee;
import com.ttbmp.cinehub.core.entity.Hall;
import com.ttbmp.cinehub.core.entity.Projectionist;
import com.ttbmp.cinehub.core.entity.Usher;

public class ShiftFactory {

    public Shift createConcreteShift(Employee employee, String date, String start, String end, Hall hall) {
        if (employee.getClass().equals(Projectionist.class)) {
            return createShiftProjectionist(employee, date, start, end, hall);
        } else if (employee.getClass().equals(Usher.class)) {
            return createShiftUsher(employee, date, start, end);
        }
        throw new IllegalStateException("Unexpected value: " + employee.getClass());
    }

    private Shift createShiftProjectionist(Employee employee, String date, String start, String end, Hall hall) {
        return new ProjectionistShift(employee, date, start, end, hall);
    }

    private Shift createShiftUsher(Employee employee, String date, String start, String end) {
        return new UsherShift(employee, date, start, end);
    }

}