package com.ttbmp.cinehub.core.entity;


/**
 * @author Massimo Mazzetti
 */

public class ShiftUsher extends Shift {

    public ShiftUsher(){}

    public ShiftUsher(Employee employee, String date, String start, String end) {
        super(employee, date, start, end);
    }

}
