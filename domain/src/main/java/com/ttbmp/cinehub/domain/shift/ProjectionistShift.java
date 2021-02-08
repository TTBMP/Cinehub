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
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ProjectionistShift elem = (ProjectionistShift) obj;

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
