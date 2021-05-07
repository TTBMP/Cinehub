package com.ttbmp.cinehub.domain.employee;

import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.security.Role;

public class Usher extends Employee {

    public Usher(String id, String name, String surname, String email, Role[] roles, Cinema cinema) {
        super(id, name, surname, email, roles, cinema);
    }

}