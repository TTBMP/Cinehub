package com.ttbmp.cinehub.app.repository.user;

import com.ttbmp.cinehub.app.service.security.Role;
import com.ttbmp.cinehub.app.service.security.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserProxy extends User {

    public UserProxy(String id, String roles) {
        super(id, getRoles(roles));
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
            case "guest":
                return Role.GUEST_ROLE;
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
