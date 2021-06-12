package com.ttbmp.cinehub.app.usecase.getuserrole;

import java.util.List;

import static com.ttbmp.cinehub.domain.security.Role.*;

public class RoleReply {

    private final List<Role> roleList;

    public RoleReply(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public enum Role {
        CUSTOMER,
        EMPLOYEE,
        PROJECTIONIST,
        USHER,
        MANAGER;

        public static Role getRole(com.ttbmp.cinehub.domain.security.Role role) {
            if (role.equals(EMPLOYEE_ROLE)) {
                return EMPLOYEE;
            }
            if (role.equals(PROJECTIONIST_ROLE)) {
                return PROJECTIONIST;
            }
            if (role.equals(USHER_ROLE)) {
                return USHER;
            }
            if (role.equals(MANAGER_ROLE)) {
                return MANAGER;
            }
            return null;
        }
    }

}
