package com.ttbmp.cinehub.core.entity;

/**
 * @author Massimo Mazzetti
 */

public class ShiftProjectionist extends Shift {

    private Hall hall;

    public ShiftProjectionist(){}

    public ShiftProjectionist(Employee employee, String date, String start, String end, Hall hall) {
        super(employee, date, start, end);
        this.hall=hall;
    }

    @Override
    public Hall getHall() {
        return hall;
    }

    @Override
    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
