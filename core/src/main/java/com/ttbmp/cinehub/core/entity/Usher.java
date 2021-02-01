package com.ttbmp.cinehub.core.entity;

import com.ttbmp.cinehub.core.ShiftFactory;

public class Usher extends Employee {

    public Usher(String name, String surname, String role, Cinema cinema, int weeklyWorkingHours) {
        super(name, surname, role, cinema, weeklyWorkingHours);
    }

    @Override
    public Shift createShift(String date, String start, String end, Hall hall) {
        Shift shift;
        ShiftFactory shiftFactory = new ShiftFactory();
        shift = shiftFactory.createShiftUsher(this, date, start, end);
        return shift;
    }
}