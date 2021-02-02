package com.ttbmp.cinehub.core.entity;

import com.ttbmp.cinehub.core.ShiftFactory;

public class Usher extends Employee {

    public Usher(String name, String surname, Cinema cinema, int weeklyWorkingHours) {
        super(name, surname, cinema, weeklyWorkingHours);
    }

    @Override
    public Shift createShift(String date, String start, String end, Hall hall) {
        Shift shift;
        ShiftFactory shiftFactory = new ShiftFactory();
        shift = shiftFactory.createShiftUsher(this, date, start, end);
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
                && this.getCinema().equals(other.getCinema())
                && this.getWeeklyWorkingHours() == other.getWeeklyWorkingHours();
    }

    @Override
    public int hashCode() {
        /* ... */
        return 0;
    }

}