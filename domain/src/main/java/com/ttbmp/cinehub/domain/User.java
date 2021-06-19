package com.ttbmp.cinehub.domain;

import com.ttbmp.cinehub.domain.security.Permission;
import com.ttbmp.cinehub.domain.security.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Fabio Buracchi
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {

    private String id;
    private String name;
    private String surname;
    private String email;
    private List<Role> roleList;

    public User(String id, String name, String surname, String email, List<Role> roleList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roleList = roleList;
    }

    public boolean hasPermission(Permission requiredPermission) {
        return roleList.stream()
                .map(Role::getPermissionList)
                .anyMatch(permissionList -> permissionList.contains(requiredPermission));
    }

}
