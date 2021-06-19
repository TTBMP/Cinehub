package com.ttbmp.cinehub.app.usecase.getuserrole;

import lombok.Value;

import java.util.List;

import static com.ttbmp.cinehub.domain.security.Role.*;

@Value
public class RoleReply {

    List<Role> roleList;

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
