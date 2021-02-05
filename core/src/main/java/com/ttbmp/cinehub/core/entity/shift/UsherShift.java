package com.ttbmp.cinehub.core.entity.shift;


import com.ttbmp.cinehub.core.entity.Employee;

/**
 * @author Massimo Mazzetti
 */

public class UsherShift extends Shift {

    public UsherShift() {
    }

    public UsherShift(Employee employee, String date, String start, String end) {
        super(employee, date, start, end);
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
                && this.getEnd().equals(elem.getEnd());
    }

    @Override
    public int hashCode() {

        return 0;
    }

}
