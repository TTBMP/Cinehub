package com.ttbmp.cinehub.domain.shift;

import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.Projection;
import com.ttbmp.cinehub.domain.employee.Employee;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Massimo Mazzetti
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ProjectionistShift extends Shift {

    private Hall hall;
    private List<Projection> projectionList;

    public ProjectionistShift(int id, Employee employee, String date, String start, String end, Hall hall, List<Projection> projectionList) {
        super(id, employee, date, start, end);
        this.hall = hall;
        this.projectionList = projectionList;
    }

    public void modifyShift(Shift shift, LocalDate date, LocalTime start, LocalTime end, Hall hall) throws ModifyShiftException {
        super.modifyShift(shift, date, start, end, hall);
        ((ProjectionistShift) shift).setHall(hall);
    }

}
