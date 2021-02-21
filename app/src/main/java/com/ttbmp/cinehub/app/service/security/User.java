package com.ttbmp.cinehub.app.service.security;

/**
 * @author Fabio Buracchi
 */
public class User {

    private String id;
    private Role[] roles;

    public User(String id, Role[] roles) {
        this.id = id;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Role[] getRoles() {
        return roles;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }

    public boolean hasPermission(Permission requiredPermission) {
        for (Role role : roles) {
            for (Permission permission : role.getPermissions()) {
                if (permission.equals(requiredPermission)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
