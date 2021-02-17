package com.ttbmp.cinehub.domain.user;

public abstract class Role {

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
