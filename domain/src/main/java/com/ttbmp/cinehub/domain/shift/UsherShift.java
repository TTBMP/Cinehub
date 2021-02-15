package com.ttbmp.cinehub.domain.shift;


import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.employee.Employee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Massimo Mazzetti
 */
public class UsherShift extends Shift {

    public UsherShift(int id, Employee employee, String date, String start, String end) {
        super(id, employee, date, start, end);
    }

    @Override
    public void modifyShift(Shift shift, LocalDate date, LocalTime start, LocalTime end, Hall hall) throws ModifyShiftException {
        List<Shift> shiftList = shift.getEmployee().getShiftListBetween(date.minusDays(1), date.plusDays(1));
        for (Shift elem : shiftList) {
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
    }

}
