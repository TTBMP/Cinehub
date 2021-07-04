package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.di.ServiceLocator;
import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.security.Role;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
@EqualsAndHashCode(callSuper = true)
public class UserProxy extends User {

    @SuppressWarnings("unused")
    public UserProxy(ServiceLocator serviceLocator, String id, String name, String surname, String email, List<Role> roleList) {
        super(id, name, surname, email, roleList);
    }

}
