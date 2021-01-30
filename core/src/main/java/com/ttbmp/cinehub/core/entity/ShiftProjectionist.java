package com.ttbmp.cinehub.core.entity;

/**
 * @author Massimo Mazzetti
 */

public class ShiftProjectionist extends Shift {

    private Hall hall;

    public ShiftProjectionist() {
    }

    public ShiftProjectionist(Employee employee, String date, String start, String end, Hall hall) {
        super(employee, date, start, end);
        this.hall = hall;
    }

    @Override
    public Hall getHall() {
        return hall;
    }

    @Override
    public void setHall(Hall hall) {
        this.hall = hall;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Shift elem = (Shift) obj;

        return this.getEmployee().equals(elem.getEmployee())
                && this.getDate().equals(elem.getDate())
                && this.getStart().equals(elem.getStart())
                && this.getEnd().equals(elem.getEnd())
                && this.getHall().equals(elem.getHall());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
