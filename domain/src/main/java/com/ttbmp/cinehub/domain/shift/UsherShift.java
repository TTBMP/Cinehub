package com.ttbmp.cinehub.domain.shift;


import com.ttbmp.cinehub.domain.Employee;

/**
 * @author Massimo Mazzetti
 */
public class UsherShift extends Shift {

    public UsherShift(Employee employee, String date, String start, String end) {
        super(employee, date, start, end);
    }

}
