package com.ttbmp.cinehub.ui.web.domain;

import lombok.Data;

/**
 * @author Fabio Buracchi
 */
@Data
public class Shift {

    private int id;
    private String date;
    private String start;
    private String end;
    private String employeeId;
    private Hall hall;

}
