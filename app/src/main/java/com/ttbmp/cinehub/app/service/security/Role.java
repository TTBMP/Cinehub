package com.ttbmp.cinehub.app.service.security;

import static com.ttbmp.cinehub.app.service.security.Permission.*;

/**
 * @author Fabio Buracchi
 */
public class Role {

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
