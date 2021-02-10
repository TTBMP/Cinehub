package com.ttbmp.cinehub.domain.shift;

import com.ttbmp.cinehub.domain.Employee;
import com.ttbmp.cinehub.domain.Hall;

/**
 * @author Massimo Mazzetti
 */
public class ProjectionistShift extends Shift {

    private Hall hall;

    public ProjectionistShift(Employee employee, String date, String start, String end, Hall hall) {
        super(employee, date, start, end);
        this.hall = hall;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
