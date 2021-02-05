package com.ttbmp.cinehub.core.entity;

import com.ttbmp.cinehub.core.entity.shift.Shift;
import com.ttbmp.cinehub.core.entity.shift.ShiftFactory;
import com.ttbmp.cinehub.core.entity.shift.ProjectionistShift;


public class Projectionist extends Employee {

    public Projectionist(String name, String surname, Cinema cinema) {
        super(name, surname, cinema);
    }

    @Override
    public Shift createShift(String date, String start, String end, Hall hall) {
        Shift shift;
        ShiftFactory shiftFactory = new ShiftFactory();
        shift = shiftFactory.createConcreteShift(ProjectionistShift.class);
        shift.setEmployee(this);
        shift.setDate(date);
        shift.setStart(start);
        shift.setEnd(end);
        ((ProjectionistShift) shift).setHall(hall);
        return shift;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Employee other = (Employee) obj;
        return this.getName().equals(other.getName())
                && this.getSurname().equals(other.getSurname())
                && this.getCinema().equals(other.getCinema());
    }

    @Override
    public int hashCode() {
        /* ... */
        return 0;
    }
}
