package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.security.Role;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class UserProxy extends User {

    public UserProxy(String id, String name, String surname, String email, List<Role> roleList) {
        super(id, name, surname, email, roleList);
    }

}
