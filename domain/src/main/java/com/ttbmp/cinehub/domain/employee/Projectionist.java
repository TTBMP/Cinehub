package com.ttbmp.cinehub.domain.employee;

import com.ttbmp.cinehub.domain.Cinema;
import com.ttbmp.cinehub.domain.security.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Projectionist extends Employee {

    public Projectionist(String id, String name, String surname, String email, List<Role> roleList, Cinema cinema) {
        super(id, name, surname, email, roleList, cinema);
    }

}
