package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.domain.User;
import com.ttbmp.cinehub.domain.security.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Fabio Buracchi
 */
public class UserProxy extends User {

    public UserProxy(String id, String name, String surname, String email, String roles) {
        super(id, name, surname, email, getRoles(roles));
    }

    private static Role[] getRoles(String rolesString) {
        List<Role> roleList = new ArrayList<>();
        Arrays.stream(rolesString.split(";"))
                .map(UserProxy::getRole)
                .forEach(roleList::add);
        return roleList.toArray(new Role[0]);
    }

    private static Role getRole(String roleString) {
        switch (roleString) {
            case "customer":
                return Role.CUSTOMER_ROLE;
            case "employee":
                return Role.EMPLOYEE_ROLE;
            case "projectionist":
                return Role.PROJECTIONIST_ROLE;
            case "usher":
                return Role.USHER_ROLE;
            case "manager":
                return Role.MANAGER_ROLE;
            default:
                throw new IllegalStateException("Unexpected value: " + roleString);
        }
    }

}
