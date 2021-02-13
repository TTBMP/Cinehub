package com.ttbmp.cinehub.domain.shift;


import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.employee.Employee;
import com.ttbmp.cinehub.domain.employee.Projectionist;
import com.ttbmp.cinehub.domain.employee.Usher;

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
        return new ProjectionistShift(0, employee, date, start, end, hall, null);
    }

    private Shift createShiftUsher(Employee employee, String date, String start, String end) {
        return new UsherShift(0, employee, date, start, end);
    }

}
