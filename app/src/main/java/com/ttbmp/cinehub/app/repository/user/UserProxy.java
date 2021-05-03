package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.domain.User;

/**
 * @author Fabio Buracchi
 */
public class UserProxy extends User {

    public UserProxy(String id, String name, String surname, String email) {
        super(id, name, surname, email);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
