package com.ttbmp.cinehub.domain.shift;

import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Employee;

import java.util.List;

/**
 * @author Massimo Mazzetti
 */
public class ProjectionistShift extends Shift {

    private Hall hall;
    private List<Projection> projectionList;

    public ProjectionistShift(int id, Employee employee, String date, String start, String end, Hall hall, List<Projection> projectionList) {
        super(id, employee, date, start, end);
        this.hall = hall;
        this.projectionList = projectionList;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public List<Projection> getProjectionList() {
        return projectionList;
    }

    public void setProjectionList(List<Projection> projectionList) {
        this.projectionList = projectionList;
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
