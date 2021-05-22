package com.ttbmp.cinehub.domain.employee;

import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.security.Role;

import java.util.List;

public class Usher extends Employee {

    public Usher(String id, String name, String surname, String email, List<Role> roleList, Cinema cinema) {
        super(id, name, surname, email, roleList, cinema);
    }

}