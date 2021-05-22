package com.ttbmp.cinehub.domain.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ttbmp.cinehub.domain.security.Permission.*;

/**
 * @author Fabio Buracchi
 */
public class Role {

    public static final Role CUSTOMER_ROLE = new Role(new ArrayList<>(Arrays.asList(
            SHOW_BUY_TICKET_TAB,
            BUY_TICKET
    )));

    public static final Role EMPLOYEE_ROLE = new Role(new ArrayList<>(Arrays.asList(
            SHOW_PERSONAL_SCHEDULE_TAB,
            GET_OWN_SHIFT_LIST
    )));

    public static final Role PROJECTIONIST_ROLE = new Role(new ArrayList<>(Arrays.asList(
            GET_OWN_SHIFT_PROJECTION_LIST
    )));

    public static final Role USHER_ROLE = new Role(new ArrayList<>());

    public static final Role MANAGER_ROLE = new Role(new ArrayList<>(Arrays.asList(
            SHOW_MANAGE_EMPLOYEE_SHIFT_TAB,
            GET_EMPLOYEE_LIST,
            GET_SHIFT_LIST,
            ASSIGN_SHIFT,
            MODIFY_SHIFT,
            DELETE_SHIFT
    )));

    private List<Permission> permissionList;

    protected Role(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

}
