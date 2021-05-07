package com.ttbmp.cinehub.domain.security;

import static com.ttbmp.cinehub.domain.security.Permission.*;

/**
 * @author Fabio Buracchi
 */
public class Role {

    public static final Role GUEST_ROLE = new Role(
            new Permission[]{

            }
    );

    public static final Role CUSTOMER_ROLE = new Role(
            new Permission[]{

            }
    );

    public static final Role EMPLOYEE_ROLE = new Role(
            new Permission[]{
                    SHOW_PERSONAL_SCHEDULE_TAB,
                    GET_OWN_SHIFT_LIST
            }
    );

    public static final Role PROJECTIONIST_ROLE = new Role(
            new Permission[]{
                    GET_OWN_SHIFT_PROJECTION_LIST
            }
    );

    public static final Role USHER_ROLE = new Role(
            new Permission[]{

            }
    );

    public static final Role MANAGER_ROLE = new Role(
            new Permission[]{
                    SHOW_MANAGE_EMPLOYEE_SHIFT_TAB
            }
    );

    private Permission[] permissions;

    protected Role(Permission[] permissions) {
        this.permissions = permissions;
    }

    public Permission[] getPermissions() {
        return permissions;
    }

    public void setPermissions(Permission[] permissions) {
        this.permissions = permissions;
    }

}
