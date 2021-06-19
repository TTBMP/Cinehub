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

    @Override
    public void modifyShift(Shift shift, LocalDate date, LocalTime start, LocalTime end, Hall hall) throws ModifyShiftException {
        var shiftList = shift.getEmployee().getShiftListBetween(date.minusDays(1), date.plusDays(1));
        for (var elem : shiftList) {
            if (elem.getId() != shift.getId()
                    && LocalDate.parse(elem.getDate()).equals(date)
                    && (start.isBefore(LocalTime.parse(elem.getEnd()))
                    && (end.isAfter(LocalTime.parse(elem.getStart()))))) {
                throw new ModifyShiftException(ModifyShiftException.ALREADY_EXIST_ERROR);
            }
        }
        shift.setDate(date.toString());
        shift.setStart(start.toString());
        shift.setEnd(end.toString());
        ((ProjectionistShift) shift).setHall(hall);
    }

}
