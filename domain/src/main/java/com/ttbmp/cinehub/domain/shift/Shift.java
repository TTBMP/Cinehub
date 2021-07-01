package com.ttbmp.cinehub.domain.shift;

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

    public void modifyShift(LocalDate date, LocalTime start, LocalTime end) throws ModifyShiftException {
        if (LocalDate.now().plusDays(1).isAfter(LocalDate.parse(this.getDate()))) {
            throw new ModifyShiftException(ModifyShiftException.INVALID_DATE);
        }
        var shiftList = this.getEmployee().getShiftListBetween(date.minusDays(1), date.plusDays(1));
        for (var elem : shiftList) {
            if (elem.getId() != this.getId()
                    && LocalDate.parse(elem.getDate()).equals(date)
                    && (start.isBefore(LocalTime.parse(elem.getEnd()))
                    && (end.isAfter(LocalTime.parse(elem.getStart()))))) {
                throw new ModifyShiftException(ModifyShiftException.ALREADY_EXIST_ERROR);
            }
        }
        this.setDate(date.toString());
        this.setStart(start.toString());
        this.setEnd(end.toString());

    }
}
