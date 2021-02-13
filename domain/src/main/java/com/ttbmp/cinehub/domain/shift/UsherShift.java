package com.ttbmp.cinehub.domain.shift;


import com.ttbmp.cinehub.domain.employee.Employee;

/**
 * @author Massimo Mazzetti
 */
public class UsherShift extends Shift {

    public UsherShift(int id, Employee employee, String date, String start, String end) {
        super(id, employee, date, start, end);
    }

}
