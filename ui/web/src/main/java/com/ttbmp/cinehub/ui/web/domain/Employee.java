package com.ttbmp.cinehub.ui.web.domain;

import lombok.Data;

/**
 * @author Fabio Buracchi
 */
@Data
public class Employee {

    private String id;
    private String name;
    private String surname;
    private String role;
    private Cinema cinema;

}
