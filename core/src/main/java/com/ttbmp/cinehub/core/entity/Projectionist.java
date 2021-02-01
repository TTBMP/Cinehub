package com.ttbmp.cinehub.core.entity;

import com.ttbmp.cinehub.core.ShiftFactory;

public class Projectionist extends Employee {

    public Projectionist(String name, String surname, String role, Cinema cinema, int weeklyWorkingHours) {
        super(name, surname, role, cinema, weeklyWorkingHours);
    }

    @Override
    public Shift createShift(String date, String start, String end, Hall hall) {
        Shift shift;
        ShiftFactory shiftFactory = new ShiftFactory();
        shift = shiftFactory.createShiftProjectionist(this, date, start, end, hall);
        return shift;
    }


}
