package com.ttbmp.cinehub.domain.shift;

import com.ttbmp.cinehub.domain.Hall;
import com.ttbmp.cinehub.domain.employee.Employee;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Fabio Buracchi, Massimo Mazzetti
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class Shift {

    private int id;
    private Employee employee;
    private String date;
    private String start;
    private String end;

    protected Shift(int id, Employee employee, String date, String start, String end) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public void modifyShift(Shift shift, LocalDate date, LocalTime start, LocalTime end) throws ModifyShiftException {
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
    }

}
