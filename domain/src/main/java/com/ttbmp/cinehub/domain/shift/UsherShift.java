package com.ttbmp.cinehub.domain.shift;

import com.ttbmp.cinehub.domain.employee.Employee;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Massimo Mazzetti
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class UsherShift extends Shift {

    public UsherShift(int id, Employee employee, String date, String start, String end) {
        super(id, employee, date, start, end);
    }

}
