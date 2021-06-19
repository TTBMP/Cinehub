package com.ttbmp.cinehub.domain.security;

import lombok.Value;

import java.util.List;

import static com.ttbmp.cinehub.domain.security.Permission.*;

/**
 * @author Fabio Buracchi
 */
@Value
public class Role {

    public static final Role CUSTOMER_ROLE = new Role(List.of(
            SHOW_BUY_TICKET_TAB,
            BUY_TICKET
    ));
    public static final Role EMPLOYEE_ROLE = new Role(List.of(
            SHOW_PERSONAL_SCHEDULE_TAB,
            GET_OWN_SHIFT_LIST
    ));
    public static final Role PROJECTIONIST_ROLE = new Role(List.of(
            GET_OWN_SHIFT_PROJECTION_LIST
    ));
    public static final Role USHER_ROLE = new Role(List.of());
    public static final Role MANAGER_ROLE = new Role(List.of(
            SHOW_MANAGE_EMPLOYEE_SHIFT_TAB,
            GET_EMPLOYEE_LIST,
            GET_SHIFT_LIST,
            ASSIGN_SHIFT,
            MODIFY_SHIFT,
            DELETE_SHIFT
    ));

    List<Permission> permissionList;

}
